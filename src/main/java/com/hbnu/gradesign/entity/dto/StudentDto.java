package com.hbnu.gradesign.entity.dto;

import com.hbnu.gradesign.entity.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto extends Student {

	/**
	 * 日期字符串
	 */
	private String dateStr;

	/**
	 * 院系名称
	 */
	private String department;

	/**
	 * 专业名称
	 */
	private String major;

	/**
	 * 班级名称
	 */
	private String cla;

}
