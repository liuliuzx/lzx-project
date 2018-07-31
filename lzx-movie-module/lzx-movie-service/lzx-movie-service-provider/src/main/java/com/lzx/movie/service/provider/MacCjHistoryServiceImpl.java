package com.lzx.movie.service.provider;

import io.jboot.aop.annotation.Bean;
import com.lzx.movie.service.api.MacCjHistoryService;
import com.lzx.movie.service.entity.model.MacCjHistory;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class MacCjHistoryServiceImpl extends JbootServiceBase<MacCjHistory> implements MacCjHistoryService {

}