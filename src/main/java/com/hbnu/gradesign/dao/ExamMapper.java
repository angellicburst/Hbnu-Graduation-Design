package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.entity.Exam;
import com.hbnu.gradesign.entity.dto.ExamDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamMapper {

    List<ExamDto> getExams(ExamDto examDto);

    List<ExamDto> getEndExams(ExamDto examDto);

    Exam getExam(Integer id);

    Integer addExam(ExamDto examDto);

    Integer deleteExam(@Param("ids")List<Integer> ids);

    Integer updateExam(ExamDto examDto);

    List<Integer> getExamCountGroupByYear();

    Integer getExamCount();

}