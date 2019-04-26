package com.hbnu.gradesign.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 教师表
 * teacher
 * @author Fynce
 * @date 2019/04/26
 */
@Getter
@Setter
public class Teacher implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 教师姓名
     * name
     */
    private String name;

    /**
     * 教师性别
            ---0：男
            ---1：女
     * gender
     */
    private Integer gender;

    /**
     * 教师工龄
     * year
     */
    private Integer year;

    /**
     * 教师电话
     * phone
     */
    private String phone;

    /**
     * 教师邮箱
     * email
     */
    private String email;

    /**
     * 状态
            ---0：有效
            ---1：失效
     * status
     */
    private Integer status;

    /**
     * 教师院系
     * departmentId
     */
    private Integer departmentId;

    /**
     * 用户ID
     * user_id
     */
    private Integer userId;

}