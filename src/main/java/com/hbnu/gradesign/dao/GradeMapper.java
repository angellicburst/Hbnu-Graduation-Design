package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.entity.Grade;
import com.hbnu.gradesign.entity.dto.GradeDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeMapper {

      Integer addGradeByCla(Grade grade);

      Integer deleteGradeByClaId(Integer claId);

      Integer deleteGradeByCouIdClaId(Integer courseId,Integer claId);

      Integer updateGrade(Grade grade);

      List<Grade> getGradeDataByExam(Integer examId,Integer claId);

      List<GradeDto> getGradesByExamIdAndClaId(Integer courseId,Integer claId);

      Integer getNumGradeIsNull();
}