package com.lzx.movie.service.entity.model.base;

import io.jboot.db.model.JbootModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by Jboot, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseMacRole<M extends BaseMacRole<M>> extends JbootModel<M> implements IBean {

	public void setRoleId(java.lang.Long roleId) {
		set("role_id", roleId);
	}
	
	public java.lang.Long getRoleId() {
		return getLong("role_id");
	}

	public void setRoleRid(java.lang.Long roleRid) {
		set("role_rid", roleRid);
	}
	
	public java.lang.Long getRoleRid() {
		return getLong("role_rid");
	}

	public void setRoleName(java.lang.String roleName) {
		set("role_name", roleName);
	}
	
	public java.lang.String getRoleName() {
		return getStr("role_name");
	}

	public void setRoleEn(java.lang.String roleEn) {
		set("role_en", roleEn);
	}
	
	public java.lang.String getRoleEn() {
		return getStr("role_en");
	}

	public void setRoleStatus(java.lang.Boolean roleStatus) {
		set("role_status", roleStatus);
	}
	
	public java.lang.Boolean getRoleStatus() {
		return get("role_status");
	}

	public void setRoleLock(java.lang.Boolean roleLock) {
		set("role_lock", roleLock);
	}
	
	public java.lang.Boolean getRoleLock() {
		return get("role_lock");
	}

	public void setRoleLetter(java.lang.String roleLetter) {
		set("role_letter", roleLetter);
	}
	
	public java.lang.String getRoleLetter() {
		return getStr("role_letter");
	}

	public void setRoleColor(java.lang.String roleColor) {
		set("role_color", roleColor);
	}
	
	public java.lang.String getRoleColor() {
		return getStr("role_color");
	}

	public void setRoleActor(java.lang.String roleActor) {
		set("role_actor", roleActor);
	}
	
	public java.lang.String getRoleActor() {
		return getStr("role_actor");
	}

	public void setRoleRemarks(java.lang.String roleRemarks) {
		set("role_remarks", roleRemarks);
	}
	
	public java.lang.String getRoleRemarks() {
		return getStr("role_remarks");
	}

	public void setRolePic(java.lang.String rolePic) {
		set("role_pic", rolePic);
	}
	
	public java.lang.String getRolePic() {
		return getStr("role_pic");
	}

	public void setRoleSort(java.lang.Integer roleSort) {
		set("role_sort", roleSort);
	}
	
	public java.lang.Integer getRoleSort() {
		return getInt("role_sort");
	}

	public void setRoleLevel(java.lang.Boolean roleLevel) {
		set("role_level", roleLevel);
	}
	
	public java.lang.Boolean getRoleLevel() {
		return get("role_level");
	}

	public void setRoleTime(java.lang.Long roleTime) {
		set("role_time", roleTime);
	}
	
	public java.lang.Long getRoleTime() {
		return getLong("role_time");
	}

	public void setRoleTimeAdd(java.lang.Long roleTimeAdd) {
		set("role_time_add", roleTimeAdd);
	}
	
	public java.lang.Long getRoleTimeAdd() {
		return getLong("role_time_add");
	}

	public void setRoleTimeHits(java.lang.Long roleTimeHits) {
		set("role_time_hits", roleTimeHits);
	}
	
	public java.lang.Long getRoleTimeHits() {
		return getLong("role_time_hits");
	}

	public void setRoleTimeMake(java.lang.Long roleTimeMake) {
		set("role_time_make", roleTimeMake);
	}
	
	public java.lang.Long getRoleTimeMake() {
		return getLong("role_time_make");
	}

	public void setRoleHits(java.lang.Integer roleHits) {
		set("role_hits", roleHits);
	}
	
	public java.lang.Integer getRoleHits() {
		return getInt("role_hits");
	}

	public void setRoleHitsDay(java.lang.Integer roleHitsDay) {
		set("role_hits_day", roleHitsDay);
	}
	
	public java.lang.Integer getRoleHitsDay() {
		return getInt("role_hits_day");
	}

	public void setRoleHitsWeek(java.lang.Integer roleHitsWeek) {
		set("role_hits_week", roleHitsWeek);
	}
	
	public java.lang.Integer getRoleHitsWeek() {
		return getInt("role_hits_week");
	}

	public void setRoleHitsMonth(java.lang.Integer roleHitsMonth) {
		set("role_hits_month", roleHitsMonth);
	}
	
	public java.lang.Integer getRoleHitsMonth() {
		return getInt("role_hits_month");
	}

	public void setRoleScore(java.math.BigDecimal roleScore) {
		set("role_score", roleScore);
	}
	
	public java.math.BigDecimal getRoleScore() {
		return get("role_score");
	}

	public void setRoleScoreAll(java.lang.Integer roleScoreAll) {
		set("role_score_all", roleScoreAll);
	}
	
	public java.lang.Integer getRoleScoreAll() {
		return getInt("role_score_all");
	}

	public void setRoleScoreNum(java.lang.Integer roleScoreNum) {
		set("role_score_num", roleScoreNum);
	}
	
	public java.lang.Integer getRoleScoreNum() {
		return getInt("role_score_num");
	}

	public void setRoleUp(java.lang.Integer roleUp) {
		set("role_up", roleUp);
	}
	
	public java.lang.Integer getRoleUp() {
		return getInt("role_up");
	}

	public void setRoleDown(java.lang.Integer roleDown) {
		set("role_down", roleDown);
	}
	
	public java.lang.Integer getRoleDown() {
		return getInt("role_down");
	}

	public void setRoleTpl(java.lang.String roleTpl) {
		set("role_tpl", roleTpl);
	}
	
	public java.lang.String getRoleTpl() {
		return getStr("role_tpl");
	}

	public void setRoleJumpurl(java.lang.String roleJumpurl) {
		set("role_jumpurl", roleJumpurl);
	}
	
	public java.lang.String getRoleJumpurl() {
		return getStr("role_jumpurl");
	}

	public void setRoleContent(java.lang.String roleContent) {
		set("role_content", roleContent);
	}
	
	public java.lang.String getRoleContent() {
		return getStr("role_content");
	}

}
