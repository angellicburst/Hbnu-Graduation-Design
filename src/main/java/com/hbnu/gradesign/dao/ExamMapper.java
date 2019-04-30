package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.entity.dto.ExamDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamMapper {

    List<ExamDto> getExams(ExamDto examDto);

}