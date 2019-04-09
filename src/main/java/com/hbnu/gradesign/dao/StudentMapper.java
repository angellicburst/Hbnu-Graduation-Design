package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.domain.Student;
import com.hbnu.gradesign.domain.dto.StudentDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Student record);

    Student selectByPrimaryKey(String id);

    List<StudentDto> getStudentsAdm(Student student);

    int updateByPrimaryKey(Student record);
}