package com.lzx.admin.controller.system;

import com.jfinal.plugin.activerecord.Page;
import com.lzx.admin.base.rest.datatable.DataTable;
import com.lzx.admin.base.web.base.BaseController;
import com.lzx.admin.service.api.LogService;
import com.lzx.admin.service.entity.model.Log;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

/**
 * 日志管理
 * @author Rlax
 *
 */
@RequestMapping("/system/log")
public class LogController extends BaseController {
    
    @JbootrpcService
    private LogService logService;

    /**
     * index
     */
    public void index() {
        render("main.html");
    }

    /**
     * 表格数据
     */
    public void tableData() {
        int pageNumber = getParaToInt("pageNumber", 1);
        int pageSize = getParaToInt("pageSize", 30);

        Log log = new Log();
        log.setIp(getPara("ip"));
        log.setUrl(getPara("url"));
        log.setLastUpdAcct(getPara("userName"));

        Page<Log> logPage = logService.findPage(log, pageNumber, pageSize);
        renderJson(new DataTable<Log>(logPage));
    }
    
}
