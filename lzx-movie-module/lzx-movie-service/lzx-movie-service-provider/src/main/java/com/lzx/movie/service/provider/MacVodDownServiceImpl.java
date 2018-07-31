package com.lzx.movie.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;

import com.jfinal.plugin.activerecord.Page;
import com.lzx.movie.service.api.MacVodDownService;
import com.lzx.movie.service.entity.model.MacVodDown;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
@JbootrpcService
public class MacVodDownServiceImpl extends JbootServiceBase<MacVodDown> implements MacVodDownService {

	@Override
	public Page<MacVodDown> findPage(MacVodDown macVodDown, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		 Columns columns = Columns.create();

         return DAO.paginateByColumns(pageNumber, pageSize, columns.getList(), "sort desc");
	}

}