package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.entity.Student;
import com.hbnu.gradesign.entity.dto.StudentDto;
import com.hbnu.gradesign.entity.excel.StudentExcel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {

    Integer addStudent(StudentExcel students);

    List<StudentDto> getStudentsAdm(StudentDto studentDto);

    List<StudentDto> getStudentsToGradeAdm(StudentDto studentDto);

    Integer deleteStudent(String id);

    Integer updateStudent(Student student);

    List<Integer> getStuCountGroupByYear();

    List<Integer> getStuYear();

    Integer getStudentCount();
}