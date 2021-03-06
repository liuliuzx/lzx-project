package com.lzx.movie.service.entity.model.base;

import io.jboot.db.model.JbootModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by Jboot, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseMacVodRes<M extends BaseMacVodRes<M>> extends JbootModel<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}
	
	public java.lang.String getId() {
		return get("id");
	}

	public void setVodId(java.lang.Long vodId) {
		set("vod_id", vodId);
	}
	
	public java.lang.Long getVodId() {
		return getLong("vod_id");
	}

	public void setFrom(java.lang.String from) {
		set("from", from);
	}
	
	public java.lang.String getFrom() {
		return getStr("from");
	}

	public void setServer(java.lang.String server) {
		set("server", server);
	}
	
	public java.lang.String getServer() {
		return getStr("server");
	}

	public void setNote(java.lang.String note) {
		set("note", note);
	}
	
	public java.lang.String getNote() {
		return getStr("note");
	}

	public void setUrl(java.lang.String url) {
		set("url", url);
	}
	
	public java.lang.String getUrl() {
		return getStr("url");
	}

	public void setType(java.lang.String type) {
		set("type", type);
	}
	
	public java.lang.String getType() {
		return getStr("type");
	}

	public void setSort(java.lang.Integer sort) {
		set("sort", sort);
	}
	
	public java.lang.Integer getSort() {
		return getInt("sort");
	}

}
