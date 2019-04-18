package com.hbnu.gradesign.entity;

import java.io.Serializable;

/**
 * 院系专业班级表
 * department
 * @author Fynce
 * @date 2019/04/08
 */
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 院系名称
     * department
     */
    private String department;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }
}