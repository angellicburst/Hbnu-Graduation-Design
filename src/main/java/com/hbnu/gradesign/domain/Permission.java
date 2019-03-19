package com.hbnu.gradesign.domain;

import java.io.Serializable;

/**
 * 权限表
 * permission
 * @author Fynce
 * @date 2019/03/19
 */
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 权限名
     * permission
     */
    private String permission;

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
        this.permission = permission == null ? null : permission.trim();
    }
}