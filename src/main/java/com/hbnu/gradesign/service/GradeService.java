package com.hbnu.gradesign.service;

import com.hbnu.gradesign.entity.Grade;
import com.hbnu.gradesign.entity.pojo.PackData;

public interface GradeService {

	PackData getGraByExmIdAClaId(Integer courseId, Integer claId);

	PackData updateGrade(Grade grade);
}
