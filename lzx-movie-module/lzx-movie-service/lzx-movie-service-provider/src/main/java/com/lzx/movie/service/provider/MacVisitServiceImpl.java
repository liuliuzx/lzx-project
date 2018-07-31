package com.lzx.movie.service.provider;

import io.jboot.aop.annotation.Bean;
import com.lzx.movie.service.api.MacVisitService;
import com.lzx.movie.service.entity.model.MacVisit;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class MacVisitServiceImpl extends JbootServiceBase<MacVisit> implements MacVisitService {

}