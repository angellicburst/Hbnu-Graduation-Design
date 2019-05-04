package com.hbnu.gradesign.service;

import com.hbnu.gradesign.entity.ExamCla;
import com.hbnu.gradesign.entity.pojo.PackData;

public interface ExamClaService {

	PackData getExamClas(Integer examId);

	PackData addExamCla(ExamCla examCla);

	PackData delExamCla(Integer id);
}
