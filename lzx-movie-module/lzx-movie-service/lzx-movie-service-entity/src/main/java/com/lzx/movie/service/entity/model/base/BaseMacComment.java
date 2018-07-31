package com.lzx.movie.service.entity.model.base;

import io.jboot.db.model.JbootModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by Jboot, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseMacComment<M extends BaseMacComment<M>> extends JbootModel<M> implements IBean {

	public void setCommentId(java.lang.Long commentId) {
		set("comment_id", commentId);
	}
	
	public java.lang.Long getCommentId() {
		return getLong("comment_id");
	}

	public void setCommentMid(java.lang.Boolean commentMid) {
		set("comment_mid", commentMid);
	}
	
	public java.lang.Boolean getCommentMid() {
		return get("comment_mid");
	}

	public void setCommentRid(java.lang.Long commentRid) {
		set("comment_rid", commentRid);
	}
	
	public java.lang.Long getCommentRid() {
		return getLong("comment_rid");
	}

	public void setCommentPid(java.lang.Long commentPid) {
		set("comment_pid", commentPid);
	}
	
	public java.lang.Long getCommentPid() {
		return getLong("comment_pid");
	}

	public void setUserId(java.lang.Long userId) {
		set("user_id", userId);
	}
	
	public java.lang.Long getUserId() {
		return getLong("user_id");
	}

	public void setCommentStatus(java.lang.Boolean commentStatus) {
		set("comment_status", commentStatus);
	}
	
	public java.lang.Boolean getCommentStatus() {
		return get("comment_status");
	}

	public void setCommentName(java.lang.String commentName) {
		set("comment_name", commentName);
	}
	
	public java.lang.String getCommentName() {
		return getStr("comment_name");
	}

	public void setCommentIp(java.lang.Long commentIp) {
		set("comment_ip", commentIp);
	}
	
	public java.lang.Long getCommentIp() {
		return getLong("comment_ip");
	}

	public void setCommentTime(java.lang.Long commentTime) {
		set("comment_time", commentTime);
	}
	
	public java.lang.Long getCommentTime() {
		return getLong("comment_time");
	}

	public void setCommentContent(java.lang.String commentContent) {
		set("comment_content", commentContent);
	}
	
	public java.lang.String getCommentContent() {
		return getStr("comment_content");
	}

	public void setCommentUp(java.lang.Integer commentUp) {
		set("comment_up", commentUp);
	}
	
	public java.lang.Integer getCommentUp() {
		return getInt("comment_up");
	}

	public void setCommentDown(java.lang.Integer commentDown) {
		set("comment_down", commentDown);
	}
	
	public java.lang.Integer getCommentDown() {
		return getInt("comment_down");
	}

	public void setCommentReply(java.lang.Integer commentReply) {
		set("comment_reply", commentReply);
	}
	
	public java.lang.Integer getCommentReply() {
		return getInt("comment_reply");
	}

	public void setCommentReport(java.lang.Integer commentReport) {
		set("comment_report", commentReport);
	}
	
	public java.lang.Integer getCommentReport() {
		return getInt("comment_report");
	}

}
