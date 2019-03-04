package com.hbnu.gradesign.entity;

/**
 * 权限
 */
public class Permission {
	private Integer id;	//ID

	private String permission;	//权限名

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
}
