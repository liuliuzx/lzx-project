package com.lzx.admin.controller.movie.vod;

import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lzx.admin.base.common.RestResult;
import com.lzx.admin.base.common.ServiceConst;
import com.lzx.admin.base.exception.BusinessException;
import com.lzx.admin.base.interceptor.NotNullPara;
import com.lzx.admin.base.rest.datatable.DataTable;
import com.lzx.admin.base.web.base.BaseController;
import com.lzx.admin.service.api.RoleService;
import com.lzx.admin.service.api.UserService;
import com.lzx.admin.service.entity.model.Role;
import com.lzx.admin.service.entity.model.User;
import com.lzx.admin.service.entity.status.system.UserStatus;
import com.lzx.admin.support.auth.AuthUtils;
import com.lzx.admin.validator.system.ChangePwdValidator;
import com.lzx.movie.service.api.MacTypeService;
import com.lzx.movie.service.api.MacVodDownService;
import com.lzx.movie.service.api.MacVodService;
import com.lzx.movie.service.entity.model.MacVod;
import com.lzx.movie.service.entity.model.MacVodDown;
import com.lzx.movie.service.entity.model.MacVodRes;
import com.lzx.movie.service.entity.status.MacVodDownStatus;
import com.mysql.fabric.xmlrpc.base.Array;

import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 电影管理
 * @author Rlax
 * 
 */
@RequestMapping("/movie/vod/vod")
public class MovieVodController extends BaseController {

    @JbootrpcService
    private UserService userService;
    
    @JbootrpcService
    private RoleService roleService;
    
    @JbootrpcService(group = ServiceConst.SERVICE_MOVIE, version = ServiceConst.VERSION_1_0)
    private MacVodService macVodService;
    @JbootrpcService(group = ServiceConst.SERVICE_MOVIE, version = ServiceConst.VERSION_1_0)
    private MacVodDownService macVodDownService;
    @JbootrpcService(group = ServiceConst.SERVICE_MOVIE, version = ServiceConst.VERSION_1_0)
    private MacTypeService macTypeService;

    /**
     * index
     */
    public void index() {
        render("main.html");
    }

    /**
     * res表格数据
     */
    public void tableData() {
        int pageNumber = getParaToInt("pageNumber", 1);
        int pageSize = getParaToInt("pageSize", 30);

        /*User sysUser = new User();
        sysUser.setName(getPara("name"));
        sysUser.setPhone(getPara("phone"));*/
        
        MacVod macVod=new MacVod();
        macVod.setVodName(getPara("vodName"));

        Page<Record> macVodPage = macVodService.findPage(macVod, pageNumber, pageSize);
        renderJson(new DataTable<Record>(macVodPage));
    }

    /**
     * add
     */
    public void add() {
      //下载组下拉选
        List<MacVodDown> macVodDownList=macVodDownService.findAll();
        //查询分类列表
        List<Record> typeList=macTypeService.findList();
        
        List<Record> vodPlayerList=new ArrayList<Record>();
        List<Record> vodDownList=new ArrayList<Record>();
        
        setAttr("vodPlayerList", vodPlayerList).setAttr("vodDownList", vodDownList).setAttr("macVodDownList", macVodDownList).setAttr("typeList", typeList).render("add.html");
    }

    public static void main(String[] args) {
    	/*long start = System.currentTimeMillis();
    	
		System.out.println("开始下载QQ:935302218的QQ空间说说数据....");
		for (int i = 1; i < 16; i++) {
			System.out.println("正在下载....第"+i+"页说说数据"+System.currentTimeMillis());
		}
		
		long end = System.currentTimeMillis();
		long times = end - start;
		System.out.println("下载已完成...总耗时"+(times+15000)+"毫秒");*/
    	
    	System.out.println("2017年8月27日，周静仪：我想结婚了～\r\n");
    	System.out.println("2011年4月8日，周静仪：我从没有后悔！恩…廖敏说这是原则问题不能有退路！");
    	
    	System.out.println("2011年3月13日，周静仪：这一切都不是幻觉。是事实。");
    	System.out.println("2011年1月2日，周静仪：I Miss You !");
    	System.out.println("2011年1月2日，周静仪：I Miss You !");
    	System.out.println("2011年7月3日，周静仪：幻想幻想越来越爱幻想了～");
    	System.out.println("2011年8月18日，周静仪：  for you");
    	System.out.println("2011年8月20日，周静仪：  如果你在等我。");
    	System.out.println("2011年9月5日，周静仪：  居然隔的这么近…只是一堵墙而已…");
    	System.out.println("2011年8月8日，周静仪：  我有喜欢的人了 别怕");
    	System.out.println("2011年9月14日，周静仪：  正好下个月开始");
	}
    
    /**
     * 保存提交
     */
    public void postAdd() {
    	
    	MacVod macVod=getBean(MacVod.class,"macVod");
    	List<MacVodRes> macVodResList=new ArrayList<MacVodRes>();
    	
    	for (int i = 0; i < getParaToInt("downListSize"); i++) {
    		MacVodRes macVodRes=getBean(MacVodRes.class,"macVodRes["+i+"]");
    		macVodResList.add(macVodRes);
		}
    	macVod.setMacVodResList(macVodResList);
    	
    	
    	
        if (!macVodService.saveMacVod(macVod)) {
            throw new BusinessException("保存失败");
        }

        renderJson(RestResult.buildSuccess());
    }

    
    /**
     * update
     */
    @NotNullPara({"id"})
    public void update() {
        Long id = getParaToLong("id");
        MacVod macVod = macVodService.findById(id);
        
        System.out.println(">>>>>>>>>>>"+macVod.getVodLevel());
        
        //下载组下拉选
        List<MacVodDown> macVodDownList=macVodDownService.findAll();
        //资源的播放组、下载组列表
        List<Record> vodResList=macVodService.findVodResById(macVod.getVodId());
        List<Record> vodPlayerList=new ArrayList<Record>();
        List<Record> vodDownList=new ArrayList<Record>();
        for (int i = 0; i < vodResList.size(); i++) {
        	Record vodRes=vodResList.get(i);
        	if("1".equals(vodRes.get("type"))) {
        		vodDownList.add(vodRes);
        	}else if("2".equals(vodRes.get("type"))) {
        		vodPlayerList.add(vodRes);
        	}
		}
        //查询分类列表
        List<Record> typeList=macTypeService.findList();
        
        
       
        
        /*String[] macVodDownFromArray=!StrKit.isBlank(macVod.getVodDownFrom())?macVod.getVodDownFrom().split("\\$\\$\\$"):null;
        String[] macVodDownNoteArray=!StrKit.isBlank(macVod.getVodDownNote())?macVod.getVodDownNote().split("\\$\\$\\$"):null;
        String[] macVodDownUrlArray=!StrKit.isBlank(macVod.getVodDownUrl())?macVod.getVodDownUrl().split("\\$\\$\\$"):null;
        for (int i = 0; i < macVodDownFromArray.length; i++) {
        	vodDown.set("from", macVodDownFromArray[i]);
		}
        for (int i = 0; i < macVodDownNoteArray.length; i++) {
        	vodDown.set("note", macVodDownNoteArray[i]);
		}
        for (int i = 0; i < macVodDownUrlArray.length; i++) {
        	vodDown.set("url", macVodDownUrlArray[i]);
		}*/
       
    

        setAttr("typeList", typeList).setAttr("macVod", macVod).setAttr("vodPlayerList", vodPlayerList).setAttr("vodDownList", vodDownList).setAttr("macVodDownList", macVodDownList).render("update.html");
    }

    /**
     * 修改提交
     */
    public void postUpdate() {
    	MacVod macVod=getBean(MacVod.class,"macVod");
    	List<MacVodRes> macVodResList=new ArrayList<MacVodRes>();
    	
    	for (int i = 0; i < getParaToInt("downListSize"); i++) {
    		MacVodRes macVodRes=getBean(MacVodRes.class,"macVodRes["+i+"]");
    		macVodResList.add(macVodRes);
		}
    	macVod.setMacVodResList(macVodResList);
    	
    	
    	
        if (!macVodService.updateMacVod(macVod)) {
            throw new BusinessException("保存失败");
        }

        renderJson(RestResult.buildSuccess());
    }

    /**
     * 删除
     */
    @NotNullPara({"id"})
    public void delete() {
        Long id = getParaToLong("id");
        if (!userService.deleteById(id)) {
            throw new BusinessException("删除失败");
        }

        renderJson(RestResult.buildSuccess());
    }

    /**
     * 解锁电影
     */
    @NotNullPara({"id"})
    public void use() {
        Long id = getParaToLong("id");

        User sysUser = userService.findById(id);
        if (sysUser == null) {
            throw new BusinessException("用户不存在");
        }

        sysUser.setStatus(UserStatus.USED);
        sysUser.setLastUpdTime(new Date());
        sysUser.setNote("解锁系统用户");

        if (!userService.update(sysUser)) {
            throw new BusinessException("解锁失败");
        }

        renderJson(RestResult.buildSuccess());
    }

    /**
     * 锁定电影
     */
    @NotNullPara({"id"})
    public void unuse() {
        Long id = getParaToLong("id");

        User sysUser = userService.findById(id);
        if (sysUser == null) {
            throw new BusinessException("用户不存在");
        }

        sysUser.setStatus(UserStatus.LOCKED);
        sysUser.setLastUpdTime(new Date());
        sysUser.setNote("锁定系统用户");

        if (!userService.update(sysUser)) {
            throw new BusinessException("锁定失败");
        }

        renderJson(RestResult.buildSuccess());
    }

    //获取小标签Json,方便新增或者是修改时使用，比如地区、语音、年份等等
    public void getLittleTag() {
    	render("/template/movie/vod/vod/vodJson.json");
    }
   

}
