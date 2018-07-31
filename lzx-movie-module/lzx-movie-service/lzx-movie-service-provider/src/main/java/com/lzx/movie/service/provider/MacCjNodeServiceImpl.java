package com.lzx.movie.service.provider;

import io.jboot.aop.annotation.Bean;
import com.lzx.movie.service.api.MacCjNodeService;
import com.lzx.movie.service.entity.model.MacCjNode;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class MacCjNodeServiceImpl extends JbootServiceBase<MacCjNode> implements MacCjNodeService {

}