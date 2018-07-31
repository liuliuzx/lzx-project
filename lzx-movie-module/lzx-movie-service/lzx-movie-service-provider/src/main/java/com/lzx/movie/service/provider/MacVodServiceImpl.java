package com.lzx.movie.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.jfinal.upload.UploadFile;
import com.lzx.admin.base.common.kit.ImageKit;
import com.lzx.admin.base.plugin.qiniu.QiniuKit;
import com.lzx.movie.service.api.MacActorService;
import com.lzx.movie.service.api.MacVodService;
import com.lzx.movie.service.entity.model.MacActor;
import com.lzx.movie.service.entity.model.MacVod;
import com.lzx.movie.service.entity.model.MacVodRes;
import com.qiniu.api.io.PutRet;

import io.jboot.service.JbootServiceBase;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.joda.time.LocalDateTime;

@Bean
@Singleton
@JbootrpcService
public class MacVodServiceImpl extends JbootServiceBase<MacVod> implements MacVodService {
	
	
	 @Inject
	 private MacActorService macActorService;
	
	@Override
	public Page<Record> findPage(MacVod macVod, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		 String sql="select "
	        		+ "a.vod_id vodId,"
	        		+ "a.vod_name vodName,"
	        		+ "a.vod_time_hits vodTimeHits,"
	        		+ "b.type_name typeName from mac_vod a, mac_type b where 1=1 "
	        		+ "and a.type_id=b.type_id ";
		 SqlPara sqlPara=new SqlPara();
		
        if (StrKit.notBlank(macVod.getVodName())) {
            sql+="and vod_name like '%"+macVod.getVodName()+"%' ";
        }
        sql+="order by a.vod_id desc ";
        sqlPara.setSql(sql);
        
       
        return Db.paginate(pageNumber, pageSize, sqlPara);
        
        
        
        
	}

	@Override
	public List<Record> findVodResById(Long id) {
		// TODO Auto-generated method stub
		return Db.find("select * from mac_vod_res where vod_id=? order by sort asc" ,id);
	}

	@Override
	public boolean saveMacVod(MacVod macVod) {
		// TODO Auto-generated method stub

		//macVod.setVodPlayUrl("www.baidu.com");
		//macVod.setVodDownUrl("www.baidu.com");
		macVod.setVodTimeAdd(new Date());
		macVod.setVodTimeHits(new Date());
		macVod.setVodTime(new Date());
        return Db.tx(new IAtom() {
            @Override
            public boolean run() throws SQLException {
                if (!macVod.save()) {
                    return false;
                }

                //处理电影的演员信息
                if(StrKit.notNull(macVod.getVodActor())) {
                	String[] actorArray=macVod.getVodActor().split(",");
                	
                	for(String actor:actorArray) {
                		MacActor macActor=macActorService.findByName(actor);
                		if(!StrKit.notNull(macActor)) {
                    		macActor.setActorName(actor);
                    		macActor.setActorStatus(true);
                    		macActor.setActorLock(false);
                    		macActorService.save(macActor);
                		}
                		
                		//新增中间关联表数据
                		Record actorRecord=new Record();
                		actorRecord.set("id", StrKit.getRandomUUID());
                		actorRecord.set("actor_id", macActor.getActorId());
                		actorRecord.set("vod_id", macVod.getVodId());
                		Db.save("mac_vod_actor", actorRecord);
                	}
                }
                
                //处理电影的资源信息
                if (StrKit.notNull(macVod.getMacVodResList())) {
                    List<MacVodRes> list = new ArrayList<MacVodRes>();
                    for (MacVodRes macVodRes : macVod.getMacVodResList()) {
                    	macVodRes.setVodId(macVod.getVodId());
                    	macVodRes.setId(StrKit.getRandomUUID());
                    	macVodRes.setType("1");
                    	list.add(macVodRes);
                    }
                    int[] rets = Db.batchSave(list, list.size());

                    for (int ret : rets) {
                        if (ret < 1) {
                            return false;
                        }
                    }
                }
                return true;
            }
        });
	}

	
	@Override
	public boolean updateMacVod(MacVod macVod) {
		// TODO Auto-generated method stub
		//macVod.setVodPlayUrl("www.baidu.com");
		//macVod.setVodDownUrl("www.baidu.com");
		macVod.setVodTimeAdd(new Date());
		macVod.setVodTimeHits(new Date());
		macVod.setVodTime(new Date());
        return Db.tx(new IAtom() {
            @Override
            public boolean run() throws SQLException {
                if (!macVod.update()) {
                    return false;
                }

                
                //处理资源的演员信息
                if(StrKit.notNull(macVod.getVodActor())) {
                	//首先清空一遍
                	Db.delete("delete from mac_vod_actor where vod_id=?", macVod.getVodId());
                	String[] actorArray=macVod.getVodActor().split(",");
                	
                	for(String actor:actorArray) {
                		MacActor macActor=macActorService.findByName(actor);
                		if(!StrKit.notNull(macActor)) {
                			macActor=new MacActor();
                    		macActor.setActorName(actor);
                    		macActor.setActorStatus(true);
                    		macActor.setActorLock(false);
                    		macActorService.save(macActor);
                		}
                		
                		//新增中间关联表数据
                		Record actorRecord=new Record();
                		actorRecord.set("id", StrKit.getRandomUUID());
                		actorRecord.set("actor_id", macActor.getActorId());
                		actorRecord.set("vod_id", macVod.getVodId());
                		Db.save("mac_vod_actor", actorRecord);
                	}
                }else {
                	//如果为空表示清空
                	Db.delete("delete from mac_vod_actor where vod_id=?", macVod.getVodId());
                }
                
                //处理电影的资源信息
                if (StrKit.notNull(macVod.getMacVodResList())) {
                	 Db.delete("delete from mac_vod_res where vod_id=?", macVod.getVodId());
                    List<MacVodRes> list = new ArrayList<MacVodRes>();
                    for (MacVodRes macVodRes : macVod.getMacVodResList()) {
                    	macVodRes.setVodId(macVod.getVodId());
                    	macVodRes.setId(StrKit.getRandomUUID());
                    	macVodRes.setType("1");
                    	list.add(macVodRes);
                    }
                    int[] rets = Db.batchSave(list, list.size());

                    for (int ret : rets) {
                        if (ret < 1) {
                            return false;
                        }
                    }
                }else {
                	//如果为空则表示清空
                	Db.delete("delete from mac_vod_res where vod_id=?", macVod.getVodId());
                }
                return true;
            }
        });
	}
	

}