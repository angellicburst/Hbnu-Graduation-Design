package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.entity.Teacher;
import com.hbnu.gradesign.entity.dto.TeacherDto;
import com.hbnu.gradesign.entity.excel.TeacherExcel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {
    Integer deleteTeacher(String id);

    Integer addTeachers(TeacherExcel teacherExcel);

    List<TeacherDto> selectTeachers(TeacherDto teacherDto);

    Integer updateTeacher(Teacher teacher);

    List<Teacher> getAllTeachers();

    Integer getTeacherCount();
}