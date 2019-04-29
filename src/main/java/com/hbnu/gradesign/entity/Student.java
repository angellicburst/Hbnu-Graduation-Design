package com.hbnu.gradesign.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生表
 * student
 * @author Fynce
 * @date 2019/04/08
 */
@Getter
@Setter
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * id
     */
    private String id;

    /**
     * 学生姓名
     * name
     */
    private String name;

    /**
     * 学生性别
            ---0：男
            ---1：女
     * gender
     */
    private Integer gender;

    /**
     * 学生电话
     * phone
     */
    private String phone;

    /**
     * 学生邮箱
     * email
     */
    private String email;

    /**
     * 几年制
     * year
     */
    private Integer year;

    @JSONField(format = "yyyy-MM-dd")
    private Date createDate;

    /**
     * 状态
        ---0：启用
        ---1：停用
     * status
     */
    private Integer status;

    /**
     * 院系ID
     * department_id
     */
    private Integer departmentId;

    /**
     * 专业ID
     * major_id
     */
    private Integer majorId;

    /**
     * 班级ID
     * cla_id
     */
    private Integer claId;

    /**
     * 用户ID
     * user_id
     */
    private Integer userId;

}