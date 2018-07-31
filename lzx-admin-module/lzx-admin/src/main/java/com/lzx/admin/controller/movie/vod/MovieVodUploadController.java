package com.lzx.admin.controller.movie.vod;

import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.render.JsonRender;
import com.jfinal.upload.UploadFile;
import com.lzx.admin.base.common.RestResult;
import com.lzx.admin.base.common.ServiceConst;
import com.lzx.admin.base.common.kit.ImageKit;
import com.lzx.admin.base.exception.BusinessException;
import com.lzx.admin.base.interceptor.NotNullPara;
import com.lzx.admin.base.plugin.qiniu.QiniuKit;
import com.lzx.admin.base.rest.datatable.DataTable;
import com.lzx.admin.base.web.base.BaseController;
import com.lzx.admin.service.api.RoleService;
import com.lzx.admin.service.api.UserService;
import com.lzx.admin.service.entity.model.Role;
import com.lzx.admin.service.entity.model.User;
import com.lzx.admin.service.entity.status.system.UserStatus;
import com.lzx.admin.support.auth.AuthUtils;
import com.lzx.admin.validator.system.ChangePwdValidator;
import com.lzx.movie.service.api.MacVodDownService;
import com.lzx.movie.service.api.MacVodService;
import com.lzx.movie.service.entity.model.MacVod;
import com.lzx.movie.service.entity.model.MacVodDown;
import com.lzx.movie.service.entity.status.MacVodDownStatus;
import com.mysql.fabric.xmlrpc.base.Array;
import com.qiniu.api.io.PutRet;

import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.joda.time.LocalDateTime;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 电影管理
 * @author Rlax
 * 
 */
@RequestMapping("/movie/vod/file")
public class MovieVodUploadController extends BaseController {
	/**
	 * 上传图片允许的最大尺寸，目前只允许 200KB
	 */
	public static final int imageMaxSize = 5000 * 1024;
	/**
	 * 上传图片临时目录，相对于 baseUploadPath 的路径，是否以 "/" 并无影响
	 * 本项目的 baseUploadLoad 为 /var/www/project_name/upload
	 */
	public static final String uploadTempPath = "/img/temp";
	/**
	 * 相对于 webRootPath 之后的目录，与"/upload" 是与 baseUploadPath 重合的部分
	 */
	public static final String basePath = "/upload/img/";
	/**
	 * 每个子目录允许存 5000 个文件
 	 */
	public static final int FILES_PER_SUB_DIR = 5000;
	

    @JbootrpcService
    private UserService userService;
    
    @JbootrpcService
    private RoleService roleService;
    
    @JbootrpcService(group = ServiceConst.SERVICE_MOVIE, version = ServiceConst.VERSION_1_0)
    private MacVodService macVodService;
    @JbootrpcService(group = ServiceConst.SERVICE_MOVIE, version = ServiceConst.VERSION_1_0)
    private MacVodDownService macVodDownService;

    /**
     * index
     */
    public void upload() {
    	
    	String from=getPara("from");
    	Long id=getParaToLong("id");
    	
		UploadFile uploadFile = null;
		try {
			// "upfile" 来自 config.json 中的 imageFieldName 配置项
			uploadFile = getFile("upfile", uploadTempPath, imageMaxSize);
			
			System.out.println(">>>>>>>>"+uploadFile.getUploadPath());
			
			Ret ret=uploadServ("vod", uploadFile);
			
			
			renderJson(RestResult.buildSuccess(ret));	// 防止 IE 下出现文件下载现象
		}
		catch(com.jfinal.upload.ExceededSizeException ex) {
			   //renderJson("state", "上传图片只允许 200K 大小");
			   throw new BusinessException("上传图片只允许 5M 大小");
		}
		catch(Exception e) {
			if (uploadFile != null) {
				uploadFile.getFile().delete();
			}
			//renderJson("state", "上传图片出现未知异常，请告知管理员：" + e.getMessage());
			throw new BusinessException("上传图片出现未知异常，请告知管理员" );
		}
    }

    /**
     * index
     */
    public void ueditor() {
    	String action=getPara("action");
    	/**
		 * ueditor 在页面加载时会向后端请求获取 config.json 内容
		 */
		if ("config".equals(action)) {
			render("/static/ueditor/config.json");
			return;
		}
		/**
		 * 对应 config.json 配置的 imageActionName: "uploadimage"
		 */
		if ( ! "uploadimage".equals(action)) {
			renderJson("state", "UploadController 只支持图片类型的文件上传");
			return ;
		}
    	
    	String from=getPara("from");
    	Long id=getParaToLong("id");
    	
		UploadFile uploadFile = null;
		try {
			// "upfile" 来自 config.json 中的 imageFieldName 配置项
			uploadFile = getFile("upfile", uploadTempPath, imageMaxSize);
			
			System.out.println(">>>>>>>>"+uploadFile.getUploadPath());
			
			Ret ret=uploadServ("vod", uploadFile);
			
			
			renderJson(ret);	// 防止 IE 下出现文件下载现象
		}
		catch(com.jfinal.upload.ExceededSizeException ex) {
			   //renderJson("state", "上传图片只允许 200K 大小");
			   throw new BusinessException("上传图片只允许 5M 大小");
		}
		catch(Exception e) {
			if (uploadFile != null) {
				uploadFile.getFile().delete();
			}
			//renderJson("state", "上传图片出现未知异常，请告知管理员：" + e.getMessage());
			throw new BusinessException("上传图片出现未知异常，请告知管理员" );
		}
    }
    
    
    
    private Ret uploadServ(String uploadType, UploadFile uf) {
		// TODO Auto-generated method stub
		Ret ret = checkUploadFile(uf);
		if (ret != null) {
			return ret;
		}
		
		String fileSize = uf.getFile().length() + "";
		String extName = "." + ImageKit.getExtName(uf.getFileName());

		// 相对路径 + 文件名：用于返回 ueditor 要求的 url 字段值，形如："/upload/img/project/0/123.jpg
		String[] relativePathFileName = new String[1];
		// 绝对路径 + 文件名：用于保存到文件系统
		String[] absolutePathFileName = new String[1];
		// 生成的文件名
		String[] fileName = new String[1];
		
		buildPathAndFileName(uploadType, extName, relativePathFileName, absolutePathFileName, fileName);
		saveOriginalFileToTargetFile(uf.getFile(), absolutePathFileName[0]);


		//上传到七牛云
		PutRet putRet= QiniuKit.put("lzx-movie", relativePathFileName[0], new File(absolutePathFileName[0]));
		System.out.println(">>>>>>>>"+putRet);
		/**
		 * ueditor 要求的返回格式：
		 * {"state": "SUCCESS",
		 * "title": "1465008328293017063.png",
		 * "original": "2222.png",
		 * "type": ".png",
		 * "url": "/ueditor/jsp/upload/image/20160604/1465008328293017063.png",
		 * "size": "185984" }
		 */
		return Ret.create("state", "SUCCESS")
				.set("url", relativePathFileName[0])
				.set("title", fileName[0])
				.set("original", uf.getOriginalFileName())
				.set("type", extName)
				.set("size", fileSize);
		
	}
	
	/**
	 * 检查 上传图片的合法性，返回值格式需要符合 的要求
	 */
	private Ret checkUploadFile(UploadFile uf) {
		if (uf == null || uf.getFile() == null) {
			return Ret.create("state", "上传文件为 null");
		}
		if (ImageKit.notImageExtName(uf.getFileName())) {
			uf.getFile().delete();      // 非图片类型，立即删除，避免浪费磁盘空间
			return Ret.create("state", "只支持 jpg、jpeg、png、bmp 四种图片类型");
		}
		if (uf.getFile().length() > imageMaxSize) {
			uf.getFile().delete();      // 图片大小超出范围，立即删除，避免浪费磁盘空间
			return Ret.create("state", "图片尺寸只允许5M 大小");
		}
		return null;
	}
	
	/**
	 * 根据上传类型生成完整的文件保存路径
	 * @param uploadType 上传类型，目前支持四种：project, share, feedback, document
	 */
	private void buildPathAndFileName(
			String uploadType,
			String extName,
			String[] relativePathFileName,
			String[] absolutePathFileName,
			String[] fileName) {


		LocalDateTime dt = LocalDateTime.now();
		
		String relativePath = "/" + dt.toString("yyyyMMdd") + "/";    // 生成相对对路径
		relativePath = basePath + uploadType + relativePath;

		fileName[0] = generateFileName(extName);
		relativePathFileName[0] =  relativePath + fileName[0];

		String absolutePath = PathKit.getWebRootPath() + relativePath;   // webRootPath 将来要根据 baseUploadPath 调整，改代码，暂时选先这样用着，着急上线
		File temp = new File(absolutePath);
		if (!temp.exists()) {
			temp.mkdirs();  // 如果目录不存在则创建
		}
		absolutePathFileName[0] = absolutePath + fileName[0];
	}
	
	/**
	 * 目前使用 File.renameTo(targetFileName) 的方式保存到目标文件，
	 * 如果 linux 下不支持，或者将来在 linux 下要跨磁盘保存，则需要
	 * 改成 copy 文件内容的方式并删除原来文件的方式来保存
	 */
	private void saveOriginalFileToTargetFile(File originalFile, String targetFile) {
		originalFile.renameTo(new File(targetFile));
	}
	
	/**
	 * 生成规范的文件名
	 * accountId_年月日时分秒.jpg
	 * 包含 accountId 以便于找到某人上传的图片，便于定位该用户所有文章，方便清除恶意上传
	 * 图片中添加一些 meta 信息：accountId_201604161359.jpg
	 * 目录中已经包含了模块名了，这里的 meta 只需要体现 accountId 与时间就可以了
	 */
	private String generateFileName(String extName) {
		return StrKit.getRandomUUID() + extName;
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
        
        MacVod macVod=new MacVod();
        macVod.setVodName(getPara("vodName"));

        Page<Record> macVodPage = macVodService.findPage(macVod, pageNumber, pageSize);
        renderJson(new DataTable<Record>(macVodPage));
    }

    /**
     * add
     */
    public void add() {
        List<Role> roleList = roleService.findByStatusUsed();
        
        List<MacVodDown> macVodDownList=macVodDownService.findAll();
        
        setAttr("macVodDownList", macVodDownList).render("add.html");
    }

    /**
     * 保存提交
     */
    public void postAdd() {
        User sysUser = getBean(User.class, "user");
        Long[] roles = getParaValuesToLong("userRole");

        if (userService.hasUser(sysUser.getName())) {
            throw new BusinessException("用户名已经存在");
        }

        sysUser.setLastUpdAcct(AuthUtils.getLoginUser().getName());
        if (!userService.saveUser(sysUser, roles)) {
            throw new BusinessException("保存失败");
        }

        renderJson(RestResult.buildSuccess());
    }

    public static void main(String[] args) {
		String ss="11$$$222";
		String bb="aa$$$";
		
		String[] macVodDownArray=ss.split("\\$\\$\\$");
		String[] macVodDownNoteArray=bb.split("\\$\\$\\$");

        /*Record macVodDownFrom=new Record();
        for (int i = 0; i < macVodDownArray.length; i++) {
       	 macVodDownFrom.set("from", macVodDownArray[i]);
       	 
		}*/
		
		System.out.println(macVodDownArray.length+"==="+macVodDownNoteArray.length);
	}
    
    /**
     * update
     */
    @NotNullPara({"id"})
    public void update() {
        Long id = getParaToLong("id");
        MacVod macVod = macVodService.findById(id);

        List<MacVodDown> macVodDownList=macVodDownService.findAll();
        
        List<Record> vodResList=macVodService.findVodResById(macVod.getVodId());
        
        List<Record> vodPlayerList=new ArrayList<Record>();

        List<Record> vodDownList=new ArrayList<Record>();
        
        for (int i = 0; i < vodResList.size(); i++) {
        	Record vodRes=vodResList.get(i);
        	if("1".equals(vodRes.get("type"))) {
        		vodDownList.add(vodRes);
        	}else if("2".equals(vodRes.get("type"))) {
        		vodPlayerList.add(vodRes);
        	}
		}
       
        
        /*String[] macVodDownFromArray=!StrKit.isBlank(macVod.getVodDownFrom())?macVod.getVodDownFrom().split("\\$\\$\\$"):null;
        String[] macVodDownNoteArray=!StrKit.isBlank(macVod.getVodDownNote())?macVod.getVodDownNote().split("\\$\\$\\$"):null;
        String[] macVodDownUrlArray=!StrKit.isBlank(macVod.getVodDownUrl())?macVod.getVodDownUrl().split("\\$\\$\\$"):null;
        for (int i = 0; i < macVodDownFromArray.length; i++) {
        	vodDown.set("from", macVodDownFromArray[i]);
		}
        for (int i = 0; i < macVodDownNoteArray.length; i++) {
        	vodDown.set("note", macVodDownNoteArray[i]);
		}
        for (int i = 0; i < macVodDownUrlArray.length; i++) {
        	vodDown.set("url", macVodDownUrlArray[i]);
		}*/
       
    

        setAttr("macVod", macVod).setAttr("vodPlayerList", vodPlayerList).setAttr("vodDownList", vodDownList).setAttr("macVodDownList", macVodDownList).render("update.html");
    }

    /**
     * 修改提交
     */
    public void postUpdate() {
        User sysUser = getBean(User.class, "user");
        Long[] roles = getParaValuesToLong("userRole");

        User _sysUser = userService.findById(sysUser.getId());
        if (_sysUser == null) {
            throw new BusinessException("用户不存在");
        }

        sysUser.setLastUpdAcct(AuthUtils.getLoginUser().getName());

        if (!userService.updateUser(sysUser, roles)) {
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

        User sysUser = userService.findById(id);
        if (sysUser == null) {
            throw new BusinessException("用户不存在");
        }

        sysUser.setStatus(UserStatus.USED);
        sysUser.setLastUpdTime(new Date());
        sysUser.setNote("解锁系统用户");

        if (!userService.update(sysUser)) {
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

        User sysUser = userService.findById(id);
        if (sysUser == null) {
            throw new BusinessException("用户不存在");
        }

        sysUser.setStatus(UserStatus.LOCKED);
        sysUser.setLastUpdTime(new Date());
        sysUser.setNote("锁定系统用户");

        if (!userService.update(sysUser)) {
            throw new BusinessException("锁定失败");
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
