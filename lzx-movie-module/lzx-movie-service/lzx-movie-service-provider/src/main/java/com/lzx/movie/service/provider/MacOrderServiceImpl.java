package com.lzx.movie.service.provider;

import io.jboot.aop.annotation.Bean;
import com.lzx.movie.service.api.MacOrderService;
import com.lzx.movie.service.entity.model.MacOrder;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class MacOrderServiceImpl extends JbootServiceBase<MacOrder> implements MacOrderService {

}