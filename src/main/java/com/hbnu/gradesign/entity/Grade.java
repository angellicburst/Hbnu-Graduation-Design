package com.hbnu.gradesign.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 成绩表
 * grade
 * @author Fynce
 * @date 2019/05/06
 */
@Getter
@Setter
public class Grade implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 学生ID
     * student_id
     */
    private String studentId;

    /**
     * 课程ID
     * course_id
     */
    private Integer courseId;

    /**
     * 班级ID
     * cla_id
     */
    private Integer claId;

    /**
     * 分数
     * score
     */
    private Integer score;

}