package com.lzx.admin.controller.movie.base;

import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lzx.admin.base.common.RestResult;
import com.lzx.admin.base.common.ServiceConst;
import com.lzx.admin.base.exception.BusinessException;
import com.lzx.admin.base.interceptor.NotNullPara;
import com.lzx.admin.base.rest.datatable.DataTable;
import com.lzx.admin.base.web.base.BaseController;
import com.lzx.admin.service.api.RoleService;
import com.lzx.admin.service.api.UserService;
import com.lzx.admin.service.entity.model.Role;
import com.lzx.admin.service.entity.model.User;
import com.lzx.admin.service.entity.status.system.UserStatus;
import com.lzx.admin.support.auth.AuthUtils;
import com.lzx.admin.validator.system.ChangePwdValidator;
import com.lzx.movie.service.api.MacTypeService;
import com.lzx.movie.service.api.MacVodService;
import com.lzx.movie.service.entity.model.MacType;
import com.lzx.movie.service.entity.model.MacVod;

import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.Date;
import java.util.List;

/**
 * 分类管理
 * @author Rlax
 * 
 */
@RequestMapping("/movie/base/type")
public class MovieTypeController extends BaseController {

    @JbootrpcService
    private UserService userService;
    
    @JbootrpcService
    private RoleService roleService;
    
    @JbootrpcService(group = ServiceConst.SERVICE_MOVIE, version = ServiceConst.VERSION_1_0)
    private MacTypeService macTypeService;

    /**
     * index
     */
    public void index() {
    	//查询分类列表
        List<Record> typeList=macTypeService.findList();
        setAttr("typeList", typeList);
        render("main.html");
    }

    /**
     * res表格数据
     */
    public void tableData() {
        int pageNumber = getParaToInt("pageNumber", 1);
        int pageSize = getParaToInt("pageSize", 30);

        /*User sysUser = new User();
        sysUser.setName(getPara("name"));
        sysUser.setPhone(getPara("phone"));*/
        
        MacType macType=new MacType();

        List<MacType> macTypePage = macTypeService.findAll();
        
        DataTable<MacType> dataTable=new DataTable<MacType>();
        dataTable.setData(macTypePage);
        
        renderJson(dataTable);
    }

    /**
     * add
     */
    public void add() {
        List<Role> roleList = roleService.findByStatusUsed();
        setAttr("roleList", roleList).render("add.html");
    }

    /**
     * 保存提交
     */
    public void postAdd() {
        MacType macType = getBean(MacType.class, "macType");


        if(!macTypeService.save(macType)) {
        	throw new BusinessException("保存失败");
        }

        renderJson(RestResult.buildSuccess());
    }

    /**
     * update
     */
    @NotNullPara({"id"})
    public void update() {
        Long id = getParaToLong("id");
        MacType macType = macTypeService.findById(id);


        setAttr("macType", macType).render("update.html");
    }

    /**
     * 修改提交
     */
    public void postUpdate() {
    	 MacType macType = getBean(MacType.class, "macType");

    	 MacType _macType = macTypeService.findById(macType.getTypeId());
        if (_macType == null) {
            throw new BusinessException("类型不存在");
        }


        if (!macTypeService.update(macType)) {
            throw new BusinessException("修改失败");
        }
        renderJson(RestResult.buildSuccess());
    }

    /**
     * 删除
     */
    @NotNullPara({"id"})
    public void delete() {
        Long id = getParaToLong("id");
        if (!userService.deleteById(id)) {
            throw new BusinessException("删除失败");
        }

        renderJson(RestResult.buildSuccess());
    }

    /**
     * 解锁用户
     */
    @NotNullPara({"id"})
    public void use() {
        Long id = getParaToLong("id");

        MacType macType = macTypeService.findById(id);
        if (macType == null) {
            throw new BusinessException("分类不存在");
        }

        macType.setTypeStatus(true);

        if (!macTypeService.update(macType)) {
            throw new BusinessException("解锁失败");
        }

        renderJson(RestResult.buildSuccess());
    }

    /**
     * 锁定用户
     */
    @NotNullPara({"id"})
    public void unuse() {
    	Long id = getParaToLong("id");

        MacType macType = macTypeService.findById(id);
        if (macType == null) {
            throw new BusinessException("分类不存在");
        }

        macType.setTypeStatus(false);

        if (!macTypeService.update(macType)) {
            throw new BusinessException("解锁失败");
        }

        renderJson(RestResult.buildSuccess());
    }

    /**
     * 个人资料
     */
    public void profile() {
        User sysUser = userService.findById(AuthUtils.getLoginUser().getId());
        setAttr("user", sysUser).render("profile.html");
    }

    /**
     * 个人资料修改提交
     */
    public void postProfile() {
        User sysUser = getBean(User.class, "user");
        if (!sysUser.getId().equals(AuthUtils.getLoginUser().getId())) {
            throw new BusinessException("无权操作");
        }

        sysUser.setLastUpdAcct(AuthUtils.getLoginUser().getName());
        sysUser.setLastUpdTime(new Date());
        sysUser.setNote("用户修改个人资料");

        if (!userService.update(sysUser)) {
            throw new BusinessException("资料修改失败");
        }

        renderJson(RestResult.buildSuccess());
    }

    /**
     * 修改密码
     */
    public void changepwd() {
        User sysUser = AuthUtils.getLoginUser();
        setAttr("user", sysUser).render("changepwd.html");
    }

    /**
     * 修改密码提交
     */
    @Before( {POST.class, ChangePwdValidator.class} )
    public void postChangepwd() {
        User sysUser = getBean(User.class, "user");
        if (!sysUser.getId().equals(AuthUtils.getLoginUser().getId())) {
            throw new BusinessException("无权操作");
        }

        String pwd = getPara("newPwd");


        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        SimpleHash hash = new SimpleHash("md5", pwd, salt2, 2);
        pwd = hash.toHex();
        sysUser.setPwd(pwd);
        sysUser.setSalt2(salt2);
        sysUser.setLastUpdAcct(AuthUtils.getLoginUser().getName());
        sysUser.setLastUpdTime(new Date());
        sysUser.setNote("用户修改密码");

        if (!userService.update(sysUser)) {
            throw new BusinessException("修改密码失败");
        }

        renderJson(RestResult.buildSuccess());
    }

}
