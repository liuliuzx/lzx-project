package com.lzx.movie.service.entity.model.base;

import io.jboot.db.model.JbootModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by Jboot, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseMacVodPlayer<M extends BaseMacVodPlayer<M>> extends JbootModel<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}
	
	public java.lang.String getId() {
		return getStr("id");
	}

	public void setStatus(java.lang.String status) {
		set("status", status);
	}
	
	public java.lang.String getStatus() {
		return getStr("status");
	}

	public void setFrom(java.lang.String from) {
		set("from", from);
	}
	
	public java.lang.String getFrom() {
		return getStr("from");
	}

	public void setShow(java.lang.String show) {
		set("show", show);
	}
	
	public java.lang.String getShow() {
		return getStr("show");
	}

	public void setDes(java.lang.String des) {
		set("des", des);
	}
	
	public java.lang.String getDes() {
		return getStr("des");
	}

	public void setPs(java.lang.String ps) {
		set("ps", ps);
	}
	
	public java.lang.String getPs() {
		return getStr("ps");
	}

	public void setParse(java.lang.String parse) {
		set("parse", parse);
	}
	
	public java.lang.String getParse() {
		return getStr("parse");
	}

	public void setTip(java.lang.String tip) {
		set("tip", tip);
	}
	
	public java.lang.String getTip() {
		return getStr("tip");
	}

	public void setSort(java.lang.Integer sort) {
		set("sort", sort);
	}
	
	public java.lang.Integer getSort() {
		return getInt("sort");
	}

}