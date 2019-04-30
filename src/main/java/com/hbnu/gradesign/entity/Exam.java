package com.hbnu.gradesign.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 考试信息表
 * exam
 * @author Fynce
 * @date 2019/04/30
 */
@Getter
@Setter
public class Exam implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 考试名称
     * exam_name
     */
    private String examName;

    /**
     * 描述
     * info
     */
    private String info;

    /**
     * 总分
     * total
     */
    private Integer total;

    /**
     * 课程ID
     * course_id
     */
    private Integer courseId;

    /**
     * 考试地点
     * address
     */
    private String address;

    /**
     * 开始时间
     * start_time
     */
    @JSONField(format = "hh:MM:ss")
    private Date startTime;

    /**
     * 结束时间
     * end_time
     */
    @JSONField(format = "hh:MM:ss")
    private Date endTime;

    /**
     * 教师ID
     * teacher_id
     */
    private String teacherId;

    /**
     * 创建时间
     * create_date
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date createDate;

}