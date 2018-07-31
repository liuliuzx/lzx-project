package com.lzx.movie.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;

import com.lzx.movie.service.api.MacActorService;
import com.lzx.movie.service.entity.model.MacActor;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
@JbootrpcService
public class MacActorServiceImpl extends JbootServiceBase<MacActor> implements MacActorService {

	@Override
	public MacActor findByName(String name) {
		// TODO Auto-generated method stub
		return DAO.findFirstByColumn("actor_name", name);
	}

}