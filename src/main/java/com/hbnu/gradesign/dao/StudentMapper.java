package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.entity.Student;
import com.hbnu.gradesign.entity.dto.StudentDto;
import com.hbnu.gradesign.entity.excel.StudentExcel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {

    Integer addStudent(StudentExcel students);

    List<StudentDto> getStudentsAdm(StudentDto studentDto);

    List<StudentDto> getStudentsTea(@Param("teacherId") String teacherId);

    List<StudentDto> getStudentsToGradeAdm(StudentDto studentDto);

    Student getStudentByUserId(Integer userId);

    Integer deleteStudent(String id);

    Integer updateStudent(Student student);

    List<Integer> getStuCountGroupByYear();

    List<Integer> getStuYear();

    Integer getStudentCount();
}