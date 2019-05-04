package com.hbnu.gradesign.entity.dto;

import com.hbnu.gradesign.entity.ExamCla;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamClaDto extends ExamCla {

	private String exam;

	private String department;

	private String major;

	private String cla;
}
