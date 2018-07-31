package com.lzx.movie.service.provider;

import io.jboot.aop.annotation.Bean;
import com.lzx.movie.service.api.MacUlogService;
import com.lzx.movie.service.entity.model.MacUlog;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class MacUlogServiceImpl extends JbootServiceBase<MacUlog> implements MacUlogService {

}