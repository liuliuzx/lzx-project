package com.lzx.movie.service.provider;

import io.jboot.aop.annotation.Bean;
import com.lzx.movie.service.api.MacCjContentService;
import com.lzx.movie.service.entity.model.MacCjContent;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class MacCjContentServiceImpl extends JbootServiceBase<MacCjContent> implements MacCjContentService {

}