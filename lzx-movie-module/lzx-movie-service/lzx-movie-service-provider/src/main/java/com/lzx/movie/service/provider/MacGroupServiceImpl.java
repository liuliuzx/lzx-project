package com.lzx.movie.service.provider;

import io.jboot.aop.annotation.Bean;
import com.lzx.movie.service.api.MacGroupService;
import com.lzx.movie.service.entity.model.MacGroup;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class MacGroupServiceImpl extends JbootServiceBase<MacGroup> implements MacGroupService {

}