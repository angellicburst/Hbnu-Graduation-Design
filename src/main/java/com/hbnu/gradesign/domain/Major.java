package com.hbnu.gradesign.domain;

import java.io.Serializable;

/**
 * 专业表
 * major
 * @author Fynce
 * @date 2019/04/08
 */
public class Major implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 专业
     * major
     */
    private String major;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }
}