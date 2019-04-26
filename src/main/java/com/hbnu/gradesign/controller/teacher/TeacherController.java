package com.hbnu.gradesign.controller.teacher;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbnu.gradesign.entity.dto.TeacherDto;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

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
}
