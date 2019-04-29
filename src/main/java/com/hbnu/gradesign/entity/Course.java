package com.hbnu.gradesign.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 课程表
 * course
 * @author Fynce
 * @date 2019/04/28
 */
@Getter
@Setter
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 课程名称
     * course
     */
    private String course;

    /**
     * 课程学分
     * num
     */
    private Integer num;

    /**
     * 课程类型
     * ----0：必修
     * ----1：选修
     * type
     */
    private Integer type;

}