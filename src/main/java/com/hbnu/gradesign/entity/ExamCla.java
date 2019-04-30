package com.hbnu.gradesign.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 考试班级表
 * exam_cla
 * @author Fynce
 * @date 2019/04/30
 */
@Getter
@Setter
public class ExamCla implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 考试信息ID
     * exam_id
     */
    private Integer examId;

    /**
     * 考试院系ID
     * department_id
     */
    private Integer departmentId;

    /**
     * 考试专业ID
     * major_id
     */
    private Integer majorId;

    /**
     * 考试班级ID
     * cla_id
     */
    private Integer claId;

}