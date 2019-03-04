package com.hbnu.gradesign.entity;

/**
 * 考试地点信息表
 * address
 * @author Fynce
 * @date 2019/03/04
 */
public class Address {
    private Integer id;

    /**
     * 考试地点
     * exam_address
     */
    private String examAddress;

    /**
     * 使用状态
     ---0：使用中
     ---1：未使用
     * status
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExamAddress() {
        return examAddress;
    }

    public void setExamAddress(String examAddress) {
        this.examAddress = examAddress == null ? null : examAddress.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}