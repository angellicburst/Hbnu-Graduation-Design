package com.hbnu.gradesign.service;

import com.hbnu.gradesign.domain.Major;
import com.hbnu.gradesign.domain.pojo.PackData;

public interface MajorService {
	Major getMajorById(Integer id);

	PackData getMajors();
}
