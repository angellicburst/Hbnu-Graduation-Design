package com.hbnu.gradesign.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生表
 * student
 * @author Fynce
 * @date 2019/04/08
 */
public class Student extends BaseRowModel implements Serializable {
    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", year=" + year +
                ", createDate=" + createDate +
                ", status=" + status +
                ", departmentId=" + departmentId +
                ", majorId=" + majorId +
                ", claId=" + claId +
                ", userId=" + userId +
                '}';
    }
}