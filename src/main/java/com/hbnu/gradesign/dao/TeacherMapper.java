package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.entity.dto.TeacherDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(Teacher record);
//
//    Teacher selectByPrimaryKey(Integer id);

    List<TeacherDto> selectTeachers(TeacherDto teacherDto);

//    int updateByPrimaryKey(Teacher record);
}