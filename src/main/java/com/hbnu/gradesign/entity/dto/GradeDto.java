package com.hbnu.gradesign.entity.dto;

import com.hbnu.gradesign.entity.Course;
import com.hbnu.gradesign.entity.Grade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradeDto extends Grade {

	private String student;

	private String course;

	private String cla;

	private Integer examId;

	private Course cou;

	private Integer num;

	private Integer type;
}
