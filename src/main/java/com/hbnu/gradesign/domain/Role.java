package com.hbnu.gradesign.domain;

import java.io.Serializable;

/**
 * 角色表
 * role
 * @author Fynce
 * @date 2019/03/19
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 角色名
     * role
     */
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }
}