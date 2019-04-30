package com.hbnu.gradesign.service;

import com.hbnu.gradesign.entity.dto.ExamDto;
import com.hbnu.gradesign.entity.pojo.PackData;

public interface ExamService {

	PackData getExamsAdm(ExamDto examDto);
}
