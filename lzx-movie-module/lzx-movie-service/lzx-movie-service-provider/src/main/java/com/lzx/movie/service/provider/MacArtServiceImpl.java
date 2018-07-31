package com.lzx.movie.service.provider;

import io.jboot.aop.annotation.Bean;
import com.lzx.movie.service.api.MacArtService;
import com.lzx.movie.service.entity.model.MacArt;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class MacArtServiceImpl extends JbootServiceBase<MacArt> implements MacArtService {

}