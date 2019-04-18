package com.hbnu.gradesign.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 院系专业班级表
 * department
 * @author Fynce
 * @date 2019/04/08
 */
@Getter
@Setter
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * id
     */
    private Integer id;

    /**
     * 院系名称
     * department
     */
    private String department;

}