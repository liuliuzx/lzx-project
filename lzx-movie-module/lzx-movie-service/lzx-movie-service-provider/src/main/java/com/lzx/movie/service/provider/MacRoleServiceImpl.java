package com.lzx.movie.service.provider;

import io.jboot.aop.annotation.Bean;
import com.lzx.movie.service.api.MacRoleService;
import com.lzx.movie.service.entity.model.MacRole;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class MacRoleServiceImpl extends JbootServiceBase<MacRole> implements MacRoleService {

}