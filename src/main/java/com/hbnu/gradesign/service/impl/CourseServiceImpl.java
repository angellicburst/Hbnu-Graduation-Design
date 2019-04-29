package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.CourseMapper;
import com.hbnu.gradesign.entity.Course;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.CourseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

	private static transient Log log = LogFactory.getLog(CourseServiceImpl.class);

	@Autowired
	private CourseMapper cm;

	/**
	 * 获得所有的课程
	 * @return
	 */
	@Override
	@Cacheable(cacheNames = "courses")
	public PackData getCourses() {
		PackData packData = new PackData();
		List<Course> courses = cm.getCourses();
		if (courses.isEmpty()) {
			packData.setCode(404);
			packData.setMsg("课程查询为空");
			log.error("课程查询为空");
		} else {
			packData.setCode(200);
			packData.setObjs(courses);
			packData.setMsg("课程查询成功");
		}
		return packData;
	}
}
