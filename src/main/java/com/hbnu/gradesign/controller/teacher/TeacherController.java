package com.hbnu.gradesign.controller.teacher;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbnu.gradesign.entity.Teacher;
import com.hbnu.gradesign.entity.dto.TeacherDto;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TeacherController {

	@Autowired
	private TeacherService ts;

	@RequestMapping(value = "/admin/getTeachers",method = RequestMethod.GET)
	public PackData getTeaAdmin(@RequestParam(value = "page", defaultValue = "1") String pageIndex,
								@RequestParam(value = "limit", defaultValue = "10") String pageSize,
								TeacherDto teacherDto) {
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		PackData packData = ts.getTeachersAdm(teacherDto);
		PageInfo pageInfo = new PageInfo(packData.getObjs());

		packData.setCount((int) pageInfo.getTotal());

		return packData;
	}

	@RequestMapping(value = "/admin/teacher/template/download",method = RequestMethod.GET)
	public PackData templateDownLoad(HttpServletResponse response) throws UnsupportedEncodingException {
		return ts.templateDownLoad(response);
	}

	@RequestMapping(value = "/admin/addTeachers",method = RequestMethod.POST)
	public PackData addTeachers(@RequestParam("file") MultipartFile file) throws Exception {
		return ts.addTeachersByExcel(file);
	}

	@RequestMapping(value = "/admin/delTeachers",method = RequestMethod.POST)
	public PackData delStudents(@RequestBody List<Teacher> teachers) {
		return ts.delTeacher(teachers);
	}

	@RequestMapping(value = "/admin/delTeacher",method = RequestMethod.POST)
	public PackData delStudent(@RequestBody Teacher teacher) {
		List<Teacher> teachers = new ArrayList<>();
		teachers.add(teacher);
		return ts.delTeacher(teachers);
	}
}
