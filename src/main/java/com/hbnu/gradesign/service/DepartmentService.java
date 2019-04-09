package com.hbnu.gradesign.service;

import com.hbnu.gradesign.domain.Department;
import com.hbnu.gradesign.domain.pojo.PackData;

public interface DepartmentService {
	Department getDepartmentById(Integer id);

	PackData getDepartments();
}
