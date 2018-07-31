package com.lzx.movie.service.provider;

import io.jboot.aop.annotation.Bean;
import com.lzx.movie.service.api.MacCommentService;
import com.lzx.movie.service.entity.model.MacComment;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class MacCommentServiceImpl extends JbootServiceBase<MacComment> implements MacCommentService {

}