package com.lzx.movie.service.provider;

import io.jboot.aop.annotation.Bean;
import com.lzx.movie.service.api.MacCardService;
import com.lzx.movie.service.entity.model.MacCard;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class MacCardServiceImpl extends JbootServiceBase<MacCard> implements MacCardService {

}