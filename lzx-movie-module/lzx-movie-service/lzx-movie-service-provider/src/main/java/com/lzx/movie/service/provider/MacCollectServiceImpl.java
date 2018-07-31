package com.lzx.movie.service.provider;

import io.jboot.aop.annotation.Bean;
import com.lzx.movie.service.api.MacCollectService;
import com.lzx.movie.service.entity.model.MacCollect;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class MacCollectServiceImpl extends JbootServiceBase<MacCollect> implements MacCollectService {

}