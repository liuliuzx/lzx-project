package com.lzx.movie.service.entity.model.base;

import io.jboot.db.model.JbootModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by Jboot, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseMacUser<M extends BaseMacUser<M>> extends JbootModel<M> implements IBean {

	public void setUserId(java.lang.Long userId) {
		set("user_id", userId);
	}
	
	public java.lang.Long getUserId() {
		return getLong("user_id");
	}

	public void setGroupId(java.lang.Integer groupId) {
		set("group_id", groupId);
	}
	
	public java.lang.Integer getGroupId() {
		return getInt("group_id");
	}

	public void setUserName(java.lang.String userName) {
		set("user_name", userName);
	}
	
	public java.lang.String getUserName() {
		return getStr("user_name");
	}

	public void setUserPwd(java.lang.String userPwd) {
		set("user_pwd", userPwd);
	}
	
	public java.lang.String getUserPwd() {
		return getStr("user_pwd");
	}

	public void setUserNickName(java.lang.String userNickName) {
		set("user_nick_name", userNickName);
	}
	
	public java.lang.String getUserNickName() {
		return getStr("user_nick_name");
	}

	public void setUserQq(java.lang.String userQq) {
		set("user_qq", userQq);
	}
	
	public java.lang.String getUserQq() {
		return getStr("user_qq");
	}

	public void setUserEmail(java.lang.String userEmail) {
		set("user_email", userEmail);
	}
	
	public java.lang.String getUserEmail() {
		return getStr("user_email");
	}

	public void setUserPhone(java.lang.String userPhone) {
		set("user_phone", userPhone);
	}
	
	public java.lang.String getUserPhone() {
		return getStr("user_phone");
	}

	public void setUserStatus(java.lang.Boolean userStatus) {
		set("user_status", userStatus);
	}
	
	public java.lang.Boolean getUserStatus() {
		return get("user_status");
	}

	public void setUserPortrait(java.lang.String userPortrait) {
		set("user_portrait", userPortrait);
	}
	
	public java.lang.String getUserPortrait() {
		return getStr("user_portrait");
	}

	public void setUserPortraitThumb(java.lang.String userPortraitThumb) {
		set("user_portrait_thumb", userPortraitThumb);
	}
	
	public java.lang.String getUserPortraitThumb() {
		return getStr("user_portrait_thumb");
	}

	public void setUserOpenidQq(java.lang.String userOpenidQq) {
		set("user_openid_qq", userOpenidQq);
	}
	
	public java.lang.String getUserOpenidQq() {
		return getStr("user_openid_qq");
	}

	public void setUserOpenidWeixin(java.lang.String userOpenidWeixin) {
		set("user_openid_weixin", userOpenidWeixin);
	}
	
	public java.lang.String getUserOpenidWeixin() {
		return getStr("user_openid_weixin");
	}

	public void setUserQuestion(java.lang.String userQuestion) {
		set("user_question", userQuestion);
	}
	
	public java.lang.String getUserQuestion() {
		return getStr("user_question");
	}

	public void setUserAnswer(java.lang.String userAnswer) {
		set("user_answer", userAnswer);
	}
	
	public java.lang.String getUserAnswer() {
		return getStr("user_answer");
	}

	public void setUserPoints(java.lang.Long userPoints) {
		set("user_points", userPoints);
	}
	
	public java.lang.Long getUserPoints() {
		return getLong("user_points");
	}

	public void setUserRegTime(java.lang.Long userRegTime) {
		set("user_reg_time", userRegTime);
	}
	
	public java.lang.Long getUserRegTime() {
		return getLong("user_reg_time");
	}

	public void setUserLoginTime(java.lang.Long userLoginTime) {
		set("user_login_time", userLoginTime);
	}
	
	public java.lang.Long getUserLoginTime() {
		return getLong("user_login_time");
	}

	public void setUserLoginIp(java.lang.Integer userLoginIp) {
		set("user_login_ip", userLoginIp);
	}
	
	public java.lang.Integer getUserLoginIp() {
		return getInt("user_login_ip");
	}

	public void setUserLastLoginTime(java.lang.Long userLastLoginTime) {
		set("user_last_login_time", userLastLoginTime);
	}
	
	public java.lang.Long getUserLastLoginTime() {
		return getLong("user_last_login_time");
	}

	public void setUserLastLoginIp(java.lang.Long userLastLoginIp) {
		set("user_last_login_ip", userLastLoginIp);
	}
	
	public java.lang.Long getUserLastLoginIp() {
		return getLong("user_last_login_ip");
	}

	public void setUserLoginNum(java.lang.Integer userLoginNum) {
		set("user_login_num", userLoginNum);
	}
	
	public java.lang.Integer getUserLoginNum() {
		return getInt("user_login_num");
	}

	public void setUserExtend(java.lang.Integer userExtend) {
		set("user_extend", userExtend);
	}
	
	public java.lang.Integer getUserExtend() {
		return getInt("user_extend");
	}

	public void setUserRandom(java.lang.String userRandom) {
		set("user_random", userRandom);
	}
	
	public java.lang.String getUserRandom() {
		return getStr("user_random");
	}

	public void setUserEndTime(java.lang.Long userEndTime) {
		set("user_end_time", userEndTime);
	}
	
	public java.lang.Long getUserEndTime() {
		return getLong("user_end_time");
	}

}