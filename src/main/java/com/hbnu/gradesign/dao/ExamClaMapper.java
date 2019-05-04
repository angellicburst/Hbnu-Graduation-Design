package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.entity.ExamCla;
import com.hbnu.gradesign.entity.dto.ExamClaDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamClaMapper {

	List<ExamClaDto> getExamClas(Integer examId);

	Integer deleteExamCla(Integer id);

	Integer addExamCla(ExamCla examCla);
}