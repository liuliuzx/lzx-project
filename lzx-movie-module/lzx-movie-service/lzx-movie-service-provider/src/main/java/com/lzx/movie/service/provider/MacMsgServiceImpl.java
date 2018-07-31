package com.lzx.movie.service.provider;

import io.jboot.aop.annotation.Bean;
import com.lzx.movie.service.api.MacMsgService;
import com.lzx.movie.service.entity.model.MacMsg;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class MacMsgServiceImpl extends JbootServiceBase<MacMsg> implements MacMsgService {

}