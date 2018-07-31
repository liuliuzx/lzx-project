package com.lzx.movie.service.provider;

import io.jboot.aop.annotation.Bean;
import com.lzx.movie.service.api.MacLinkService;
import com.lzx.movie.service.entity.model.MacLink;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class MacLinkServiceImpl extends JbootServiceBase<MacLink> implements MacLinkService {

}