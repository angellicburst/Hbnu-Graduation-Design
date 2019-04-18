package com.hbnu.gradesign.entity;

import java.io.Serializable;

/**
 * 班级表
 * cla
 * @author Fynce
 * @date 2019/04/08
 */
public class Cla implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 班级
     * cla
     */
    private String cla;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCla() {
        return cla;
    }

    public void setCla(String cla) {
        this.cla = cla == null ? null : cla.trim();
    }
}