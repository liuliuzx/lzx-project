package com.lzx.admin.base.plugin.qiniu;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.json.JSONException;

import com.lzx.admin.base.exception.BusinessException;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.RSClient;
import com.qiniu.api.rsf.RSFClient;


public class QiniuConfig {
	private String ak;
	private String sk;
	
	public QiniuConfig(String propertiesFile) {
		Properties properties = new Properties();
        InputStream is = QiniuConfig.class.getClassLoader().getResourceAsStream(propertiesFile);
        try {
            properties.load(is);
        } catch (IOException e) {
        	throw new BusinessException("QiniuConfig"+e);
        }
        
        ak = properties.getProperty("qiniu.accessKey");
        sk = properties.getProperty("qiniu.secretKey");
	}

	public String getAk() {
		return ak;
	}

	public void setAk(String ak) {
		this.ak = ak;
	}

	public String getSk() {
		return sk;
	}

	public void setSk(String sk) {
		this.sk = sk;
	}
	
	
	public String  getToken(String bucketName){
		Mac mac = new Mac(ak, sk);
        PutPolicy putPolicy = new PutPolicy(bucketName);
        try {
			return putPolicy.token(mac);
		} catch (JSONException e) {
			throw new BusinessException("qiniu config get token"+e);
		} catch (AuthException e) {
			throw new BusinessException("qiniu config get token"+e);
		}
	}
	
	public RSFClient getRSFClient(){
		Mac mac = new Mac(ak, sk);
		RSFClient client = new RSFClient(mac);
		return client;
	}
	
	
	public RSClient getRSClient(){
		Mac mac = new Mac(ak, sk);
		RSClient client = new RSClient(mac);
		return client;
	}
	
	

}