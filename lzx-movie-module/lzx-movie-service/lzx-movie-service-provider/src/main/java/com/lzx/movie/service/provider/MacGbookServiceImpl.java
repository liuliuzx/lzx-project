package com.lzx.movie.service.provider;

import io.jboot.aop.annotation.Bean;
import com.lzx.movie.service.api.MacGbookService;
import com.lzx.movie.service.entity.model.MacGbook;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class MacGbookServiceImpl extends JbootServiceBase<MacGbook> implements MacGbookService {

}