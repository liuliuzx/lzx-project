package com.lzx.admin.base.plugin.qiniu;

import com.jfinal.plugin.IPlugin;

public class QiniuPlugin  implements IPlugin {

	private String propFile = "jboot.properties";
	
	public QiniuPlugin() {}
	public QiniuPlugin(String propFile) {
		this.propFile = propFile;
	}
	
	@Override
	public boolean start() {
		QiniuKit.init(new QiniuConfig(propFile));
		return true;
	}

	@Override
	public boolean stop() {
		return true;
	}

}