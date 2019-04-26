package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.entity.dto.TeacherDto;
import com.hbnu.gradesign.entity.excel.TeacherExcel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {
//    int deleteByPrimaryKey(Integer id);
//
    Integer addTeachers(TeacherExcel teacherExcel);
//
//    Teacher selectByPrimaryKey(Integer id);

    List<TeacherDto> selectTeachers(TeacherDto teacherDto);

//    int updateByPrimaryKey(Teacher record);
}