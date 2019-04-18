package com.hbnu.gradesign.service;

import com.hbnu.gradesign.entity.dto.StudentDto;
import com.hbnu.gradesign.entity.pojo.PackData;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public interface StudentService {

	PackData getStusAdm(StudentDto studentDto);

	PackData addStudentsByExcel(MultipartFile multipartFile) throws Exception;

	PackData templateDownLoad(HttpServletResponse response) throws UnsupportedEncodingException;
}
