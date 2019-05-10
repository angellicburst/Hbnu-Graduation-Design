package com.hbnu.gradesign.controller.course;

import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

	@Autowired
	private CourseService cs;

	/**
	 * 获取所有课程
	 * @return
	 */
	@RequestMapping(value = "/getCourses",method = RequestMethod.POST)
	public PackData getCourses() {
		return cs.getCourses();
	}
}
