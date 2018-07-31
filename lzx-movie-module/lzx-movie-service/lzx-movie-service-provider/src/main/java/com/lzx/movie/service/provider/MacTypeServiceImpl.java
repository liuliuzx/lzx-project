package com.lzx.movie.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.lzx.movie.service.api.MacTypeService;
import com.lzx.movie.service.entity.model.MacType;
import io.jboot.service.JbootServiceBase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

@Bean
@Singleton
@JbootrpcService
public class MacTypeServiceImpl extends JbootServiceBase<MacType> implements MacTypeService {

	@Override
	public List<Record> findList() {
		// TODO Auto-generated method stub
		List<Record> parentList=Db.find("select * from mac_type where type_pid=0");
		List<Record> resultList=new ArrayList<Record>();
		for (int i = 0; i < parentList.size(); i++) {
			Record type=parentList.get(i);
			type.set("child", Db.find("select * from mac_type where type_pid=?",type.getInt("type_id")));
			resultList.add(type);
		}
		return resultList;
	}

}