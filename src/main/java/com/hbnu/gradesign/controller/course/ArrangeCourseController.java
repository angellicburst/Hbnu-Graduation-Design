package com.hbnu.gradesign.controller.course;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbnu.gradesign.entity.ArrangeCourse;
import com.hbnu.gradesign.entity.Course;
import com.hbnu.gradesign.entity.dto.ArrangeCourseDto;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.ArrangeCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ArrangeCourseController {

	@Autowired
	private ArrangeCourseService acs;

	/**
	 * admin
	 * 获取全部的课程安排
	 * @param pageIndex
	 * @param pageSize
	 * @param arrangeCourse
	 * @return
	 */
	@RequestMapping(value = "/admin/getArrangeCourses",method = RequestMethod.GET)
	public PackData getStuAdmin(@RequestParam(value = "page", defaultValue = "1") String pageIndex,
								@RequestParam(value = "limit", defaultValue = "10") String pageSize,
								ArrangeCourse arrangeCourse) {
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		PackData packData = acs.getArrangeCourses(arrangeCourse);
		PageInfo pageInfo = new PageInfo(packData.getObjs());

		packData.setCount((int) pageInfo.getTotal());

		return packData;
	}

	/**
	 * admin
	 * 添加课程安排
	 * @param arrangeCourseDto
	 * @return
	 */
	@RequestMapping(value = "/admin/addCourseArrange",method = RequestMethod.POST)
	public PackData addCourseArrange(ArrangeCourseDto arrangeCourseDto) {
		return acs.addCourseArrange(arrangeCourseDto);
	}

	/**
	 * admin
	 * 批量删除课程安排
	 * @param courses
	 * @return
	 */
	@RequestMapping(value = "/admin/delCourseArranges",method = RequestMethod.POST)
	public PackData delCourseArranges(@RequestBody List<Course> courses) {
		return acs.delCourseArrange(courses);
	}

	/**
	 * admin
	 * 删除课程安排
	 * @param course
	 * @return
	 */
	@RequestMapping(value = "/admin/delCourseArrange",method = RequestMethod.POST)
	public PackData delCourseArrange(Course course) {
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		return acs.delCourseArrange(courses);
	}

	/**
	 * 菜单更新
	 * @param arrangeCourseDto
	 * @return
	 */
	@RequestMapping(value = "/admin/editCourseArrange",method = RequestMethod.POST)
	public PackData editMenu(ArrangeCourseDto arrangeCourseDto) {
		return acs.editCourseArrange(arrangeCourseDto);
	}

}
