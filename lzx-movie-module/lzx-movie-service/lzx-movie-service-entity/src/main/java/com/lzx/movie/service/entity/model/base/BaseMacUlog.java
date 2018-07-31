package com.lzx.movie.service.entity.model.base;

import io.jboot.db.model.JbootModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by Jboot, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseMacUlog<M extends BaseMacUlog<M>> extends JbootModel<M> implements IBean {

	public void setUlogId(java.lang.Long ulogId) {
		set("ulog_id", ulogId);
	}
	
	public java.lang.Long getUlogId() {
		return getLong("ulog_id");
	}

	public void setUserId(java.lang.Long userId) {
		set("user_id", userId);
	}
	
	public java.lang.Long getUserId() {
		return getLong("user_id");
	}

	public void setUlogMid(java.lang.Boolean ulogMid) {
		set("ulog_mid", ulogMid);
	}
	
	public java.lang.Boolean getUlogMid() {
		return get("ulog_mid");
	}

	public void setUlogType(java.lang.Boolean ulogType) {
		set("ulog_type", ulogType);
	}
	
	public java.lang.Boolean getUlogType() {
		return get("ulog_type");
	}

	public void setUlogRid(java.lang.Long ulogRid) {
		set("ulog_rid", ulogRid);
	}
	
	public java.lang.Long getUlogRid() {
		return getLong("ulog_rid");
	}

	public void setUlogSid(java.lang.Integer ulogSid) {
		set("ulog_sid", ulogSid);
	}
	
	public java.lang.Integer getUlogSid() {
		return getInt("ulog_sid");
	}

	public void setUlogNid(java.lang.Integer ulogNid) {
		set("ulog_nid", ulogNid);
	}
	
	public java.lang.Integer getUlogNid() {
		return getInt("ulog_nid");
	}

	public void setUlogPoints(java.lang.Integer ulogPoints) {
		set("ulog_points", ulogPoints);
	}
	
	public java.lang.Integer getUlogPoints() {
		return getInt("ulog_points");
	}

	public void setUlogTime(java.lang.Long ulogTime) {
		set("ulog_time", ulogTime);
	}
	
	public java.lang.Long getUlogTime() {
		return getLong("ulog_time");
	}

}