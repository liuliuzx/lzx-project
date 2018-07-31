package com.lzx.movie.service.provider;

import io.jboot.aop.annotation.Bean;
import com.lzx.movie.service.api.MacTopicService;
import com.lzx.movie.service.entity.model.MacTopic;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class MacTopicServiceImpl extends JbootServiceBase<MacTopic> implements MacTopicService {

}