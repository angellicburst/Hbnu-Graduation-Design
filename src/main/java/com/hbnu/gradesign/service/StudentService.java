package com.hbnu.gradesign.service;

import com.hbnu.gradesign.domain.dto.StudentDto;
import com.hbnu.gradesign.domain.pojo.PackData;

public interface StudentService {

	PackData getStusAdm(StudentDto studentDto);
}
