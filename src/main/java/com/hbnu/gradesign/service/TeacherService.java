package com.hbnu.gradesign.service;

import com.hbnu.gradesign.entity.dto.TeacherDto;
import com.hbnu.gradesign.entity.pojo.PackData;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public interface TeacherService {

	PackData getTeachersAdm(TeacherDto teacherDto);

	PackData templateDownLoad(HttpServletResponse response) throws UnsupportedEncodingException;

	PackData addTeachersByExcel(MultipartFile file) throws Exception;
}
