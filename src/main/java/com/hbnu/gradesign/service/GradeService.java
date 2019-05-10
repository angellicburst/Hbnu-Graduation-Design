package com.hbnu.gradesign.service;

import com.hbnu.gradesign.entity.dto.GradeDto;
import com.hbnu.gradesign.entity.pojo.PackData;

public interface GradeService {


	PackData getGraByExmIdAClaId(Integer courseId, Integer claId);

	PackData getGradeByStu(String studentId);

	PackData updateGrade(GradeDto gradeDto);

	PackData updateGradeNC(GradeDto gradeDto);
}
