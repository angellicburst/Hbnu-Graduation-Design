package com.hbnu.gradesign.entity.dto;

import com.hbnu.gradesign.entity.Teacher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherDto extends Teacher {

	private String department;
}
