package com.hbnu.gradesign.service;

import com.hbnu.gradesign.entity.Exam;
import com.hbnu.gradesign.entity.dto.ExamDto;
import com.hbnu.gradesign.entity.pojo.PackData;

import java.util.List;

public interface ExamService {

	PackData getExamsAdm(ExamDto examDto);

	PackData getExamsByTeacherId(ExamDto examDto);

	PackData getEndExamsAdm(ExamDto examDto);

	PackData addExam(ExamDto examDto);

	PackData delExam(List<Exam> exams);

	PackData editExam(ExamDto examDto);
}
