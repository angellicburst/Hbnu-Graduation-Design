package com.hbnu.gradesign.entity.dto;

import com.hbnu.gradesign.entity.ArrangeCourse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArrangeCourseDto extends ArrangeCourse {

	private String teacher;

	private String course;

	private String department;

	private String major;

	private String cla;

	private String dateScope;
}
