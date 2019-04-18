package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.entity.Student;
import com.hbnu.gradesign.entity.dto.StudentDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {

    Integer addStudent(Student students);

    List<StudentDto> getStudentsAdm(Student student);
}