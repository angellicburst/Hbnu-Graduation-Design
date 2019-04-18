package com.hbnu.gradesign.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 班级表
 * cla
 * @author Fynce
 * @date 2019/04/08
 */
@Getter
@Setter
public class Cla implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * id
     */
    private Integer id;

    /**
     * 班级
     * cla
     */
    private String cla;

}