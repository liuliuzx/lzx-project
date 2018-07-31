package com.lzx.movie.service.provider;

import io.jboot.aop.annotation.Bean;
import com.lzx.movie.service.api.MacAdminService;
import com.lzx.movie.service.entity.model.MacAdmin;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class MacAdminServiceImpl extends JbootServiceBase<MacAdmin> implements MacAdminService {

}