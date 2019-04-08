package com.hbnu.gradesign.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生表
 * student
 * @author Fynce
 * @date 2019/04/08
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getClaId() {
        return claId;
    }

    public void setClaId(Integer claId) {
        this.claId = claId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}