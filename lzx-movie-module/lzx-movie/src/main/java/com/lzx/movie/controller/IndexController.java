package com.lzx.movie.controller;

import com.lzx.admin.base.web.base.BaseController;
import com.lzx.movie.service.api.MacVodService;

import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

/**
 * 主控制器
 * @author Rlax
 *
 */
@RequestMapping("/")
public class IndexController extends BaseController {

	@JbootrpcService
	MacVodService macVodService;
	
	public void index() {
		
		
		
		render("index.html");
	}
}
