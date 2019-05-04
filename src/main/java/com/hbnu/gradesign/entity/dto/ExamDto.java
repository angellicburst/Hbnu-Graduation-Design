package com.hbnu.gradesign.entity.dto;

import com.hbnu.gradesign.entity.Exam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamDto extends Exam {

	private String course;

	private String teacher;

	private String timeScope;
}
