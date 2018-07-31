package com.lzx.admin.controller.movie.vod;

import com.jfinal.plugin.activerecord.Page;
import com.lzx.admin.base.common.RestResult;
import com.lzx.admin.base.common.ServiceConst;
import com.lzx.admin.base.exception.BusinessException;
import com.lzx.admin.base.interceptor.NotNullPara;
import com.lzx.admin.base.rest.datatable.DataTable;
import com.lzx.admin.base.web.base.BaseController;
import com.lzx.movie.service.api.MacVodPlayerService;
import com.lzx.movie.service.entity.model.MacVodPlayer;
import com.lzx.movie.service.entity.status.MacVodPlayerStatus;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;


/**
 * 播放器管理
 * @author Rlax
 *
 */
@RequestMapping("/movie/vod/player")
public class MovieVodPlayerController extends BaseController {

    @JbootrpcService(group = ServiceConst.SERVICE_MOVIE, version = ServiceConst.VERSION_1_0)
    private MacVodPlayerService macVodPlayerService;
    
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
        MacVodPlayer macVodPlayer = new MacVodPlayer();

        Page<MacVodPlayer> macVodPlayerPage = macVodPlayerService.findPage(macVodPlayer, pageNumber, pageSize);
        renderJson(new DataTable<MacVodPlayer>(macVodPlayerPage));
    }

    /**
     * add
     */
    public void add() {
        render("add.html");
    }

    /**
     * 保存提交
     */
    public void postAdd() {
        MacVodPlayer macVodPlayer = getBean(MacVodPlayer.class, "macVodPlayer");

        

        if (!macVodPlayerService.save(macVodPlayer)) {
            throw new BusinessException("保存失败");
        }

        renderJson(RestResult.buildSuccess());
    }

    /**
     * update
     */
    @NotNullPara({"id"})
    public void update() {
        String id = getPara("id");
        MacVodPlayer macVodPlayer = macVodPlayerService.findById(id);

        setAttr("macVodPlayer", macVodPlayer).render("update.html");
    }

    /**
     * 修改提交
     */
    public void postUpdate() {
    	MacVodPlayer macVodPlayer = getBean(MacVodPlayer.class, "macVodPlayer");

        if (macVodPlayerService.findById(macVodPlayer.getId()) == null) {
            throw new BusinessException("数据不存在");
        }


        if (!macVodPlayerService.update(macVodPlayer)) {
            throw new BusinessException("修改失败");
        }

        renderJson(RestResult.buildSuccess());
    }

    /**
     * 删除
     */
    @NotNullPara({"id"})
    public void delete() {
        String id = getPara("id");
        if (!macVodPlayerService.deleteById(id)) {
            throw new BusinessException("删除失败");
        }

        renderJson(RestResult.buildSuccess());
    }

    /**
     * 启用资源
     */
    @NotNullPara({"id"})
    public void use() {
        String id = getPara("id");

        MacVodPlayer macVodPlayer = macVodPlayerService.findById(id);
        if (macVodPlayer == null) {
            throw new BusinessException("编号为" + id + "的数据不存在");
        }
        macVodPlayer.setStatus(MacVodPlayerStatus.USED);

        if (!macVodPlayerService.update(macVodPlayer)) {
            throw new BusinessException("启用失败");
        }

        renderJson(RestResult.buildSuccess());
    }

    /**
     * 停用资源
     */
    @NotNullPara({"id"})
    public void unuse() {
        String id = getPara("id");
        MacVodPlayer macVodPlayer = macVodPlayerService.findById(id);
        if (macVodPlayer == null) {
            throw new BusinessException("编号为" + id + "的数据不存在");
        }

        macVodPlayer.setStatus(MacVodPlayerStatus.UNUSED);

        if (!macVodPlayerService.update(macVodPlayer)) {
            throw new BusinessException("停用失败");
        }

        renderJson(RestResult.buildSuccess());
    }


}
