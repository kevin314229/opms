package com.opms.entity;

import java.io.Serializable;

public class ResourceRole implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resId;
	private String roleId;

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
