package com.lzx.movie.service.provider;

import io.jboot.aop.annotation.Bean;
import com.lzx.movie.service.api.MacUserService;
import com.lzx.movie.service.entity.model.MacUser;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class MacUserServiceImpl extends JbootServiceBase<MacUser> implements MacUserService {

}