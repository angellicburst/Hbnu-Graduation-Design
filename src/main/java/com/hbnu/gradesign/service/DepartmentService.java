package com.hbnu.gradesign.service;

import com.hbnu.gradesign.entity.Department;
import com.hbnu.gradesign.entity.pojo.PackData;

public interface DepartmentService {
	Department getDepartmentById(Integer id);

	PackData getDepartments();
}
