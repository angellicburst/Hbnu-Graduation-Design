package com.hbnu.gradesign.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
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
public class Student extends BaseRowModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * id
     */
    @ExcelProperty(index = 0 , value = "ID")
    private String id;

    /**
     * 学生姓名
     * name
     */
    @ExcelProperty(index = 1 , value = "姓名")
    private String name;

    /**
     * 学生性别
            ---0：男
            ---1：女
     * gender
     */
    @ExcelProperty(index = 2 , value = "性别（0：男    1：女）")
    private Integer gender;

    /**
     * 学生电话
     * phone
     */
    @ExcelProperty(index = 3 , value = "电话")
    private String phone;

    /**
     * 学生邮箱
     * email
     */
    @ExcelProperty(index = 4 , value = "邮箱")
    private String email;

    /**
     * 几年制
     * year
     */
    @ExcelProperty(index = 5 , value = "学制")
    private Integer year;


    @JSONField(format = "yyyy-MM-dd")
    @ExcelProperty(index = 6 , value = "入学时间(yyyy-MM-dd)", format = "yyyy-MM-dd")
    private Date createDate;

    /**
     * 状态
        ---0：启用
        ---1：停用
     * status
     */
    @ExcelProperty(index = 7 , value = "状态(0:启用   1：停用)")
    private Integer status;

    /**
     * 院系ID
     * department_id
     */
    @ExcelProperty(index = 8 , value = "院系（填写院系ID）")
    private Integer departmentId;

    /**
     * 专业ID
     * major_id
     */
    @ExcelProperty(index = 9 , value = "专业（填写专业ID）")
    private Integer majorId;

    /**
     * 班级ID
     * cla_id
     */
    @ExcelProperty(index = 10 , value = "班级（填写班级ID）")
    private Integer claId;

    /**
     * 用户ID
     * user_id
     */
    private Integer userId;

}