package com.hbnu.gradesign.service;

import com.hbnu.gradesign.entity.Student;
import com.hbnu.gradesign.entity.dto.StudentDto;
import com.hbnu.gradesign.entity.pojo.PackData;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface StudentService {

	PackData getStusAdm(StudentDto studentDto);

	PackData getStusTea(StudentDto studentDto);

	PackData getStuToGrade(StudentDto studentDto);

	Student getStuByUserId(Integer userId);

	PackData addStudentsByExcel(MultipartFile file) throws Exception;

	PackData templateDownLoad(HttpServletResponse response) throws UnsupportedEncodingException;

	PackData delStudent(List<Student> students);

	PackData updateStudent(Student student);
}
