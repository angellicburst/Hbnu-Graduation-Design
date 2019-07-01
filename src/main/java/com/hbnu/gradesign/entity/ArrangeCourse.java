package com.hbnu.gradesign.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 班级课表
 * arrange_course
 * @author Fynce
 * @date 2019/04/29
 */
@Getter
@Setter
public class ArrangeCourse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 课程ID
     * course_id
     */
    private Integer courseId;

    /**
     * 任课老师ID
     * teacher_id
     */
    private String teacherId;

    /**
     * 上课地点
     * address
     */
    private String address;

    /**
     * 课程开始时间
     * start_date
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 课程结束时间
     * end_date
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 是否考试
     * is_exam
     */
    private Integer isExam;

    /**
     * 上课方式
        ---0：每周
        ---1：单周
        ---2：双周
     * method
     */
    private Integer method;

    /**
     * 星期
        1：星期一
        2：星期二
        3：星期三
        4：星期四
        5：星期五
        6：星期六
        0：星期天
     * week
     */
    private Integer week;

    /**
     * 上课时间
        一天10节课
        1~2节：1
        3~4节：3
        5~6节：5
        7~8节：7
        9~10节：9
     * node
     */
    private Integer node;

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

}