package com.hbnu.gradesign.service;

import com.hbnu.gradesign.entity.Major;
import com.hbnu.gradesign.entity.pojo.PackData;

import java.util.List;

public interface MajorService {
	Major getMajorById(Integer id);

	PackData getMajors();

	List<Major> getMajorsByDepartmentId(Integer departmentId);
}
