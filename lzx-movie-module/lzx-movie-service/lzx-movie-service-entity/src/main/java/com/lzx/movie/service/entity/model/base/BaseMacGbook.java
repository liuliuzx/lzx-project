package com.lzx.movie.service.entity.model.base;

import io.jboot.db.model.JbootModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by Jboot, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseMacGbook<M extends BaseMacGbook<M>> extends JbootModel<M> implements IBean {

	public void setGbookId(java.lang.Long gbookId) {
		set("gbook_id", gbookId);
	}
	
	public java.lang.Long getGbookId() {
		return getLong("gbook_id");
	}

	public void setGbookRid(java.lang.Long gbookRid) {
		set("gbook_rid", gbookRid);
	}
	
	public java.lang.Long getGbookRid() {
		return getLong("gbook_rid");
	}

	public void setUserId(java.lang.Long userId) {
		set("user_id", userId);
	}
	
	public java.lang.Long getUserId() {
		return getLong("user_id");
	}

	public void setGbookStatus(java.lang.Boolean gbookStatus) {
		set("gbook_status", gbookStatus);
	}
	
	public java.lang.Boolean getGbookStatus() {
		return get("gbook_status");
	}

	public void setGbookName(java.lang.String gbookName) {
		set("gbook_name", gbookName);
	}
	
	public java.lang.String getGbookName() {
		return getStr("gbook_name");
	}

	public void setGbookIp(java.lang.Long gbookIp) {
		set("gbook_ip", gbookIp);
	}
	
	public java.lang.Long getGbookIp() {
		return getLong("gbook_ip");
	}

	public void setGbookTime(java.lang.Long gbookTime) {
		set("gbook_time", gbookTime);
	}
	
	public java.lang.Long getGbookTime() {
		return getLong("gbook_time");
	}

	public void setGbookReplyTime(java.lang.Long gbookReplyTime) {
		set("gbook_reply_time", gbookReplyTime);
	}
	
	public java.lang.Long getGbookReplyTime() {
		return getLong("gbook_reply_time");
	}

	public void setGbookContent(java.lang.String gbookContent) {
		set("gbook_content", gbookContent);
	}
	
	public java.lang.String getGbookContent() {
		return getStr("gbook_content");
	}

	public void setGbookReply(java.lang.String gbookReply) {
		set("gbook_reply", gbookReply);
	}
	
	public java.lang.String getGbookReply() {
		return getStr("gbook_reply");
	}

}
