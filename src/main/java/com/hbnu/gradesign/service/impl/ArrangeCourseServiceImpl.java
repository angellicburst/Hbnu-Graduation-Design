package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.ArrangeCourseMapper;
import com.hbnu.gradesign.entity.ArrangeCourse;
import com.hbnu.gradesign.entity.Course;
import com.hbnu.gradesign.entity.dto.ArrangeCourseDto;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.ArrangeCourseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.thymeleaf.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArrangeCourseServiceImpl implements ArrangeCourseService {

	private static transient Log log = LogFactory.getLog(ArrangeCourseServiceImpl.class);

	@Autowired
	private ArrangeCourseMapper acm;

	/**
	 * 获取全部的课程安排
	 * @param arrangeCourse
	 * @return
	 */
	@Override
	public PackData getArrangeCourses(ArrangeCourse arrangeCourse) {
		PackData packData = new PackData();

		List<ArrangeCourseDto> arrangeCourseDtos = acm.getArrangeCourses(arrangeCourse);
		if (arrangeCourseDtos.isEmpty()) {
			packData.setCode(404);
			packData.setMsg("课程安排查询为空");
			log.error("课程安排查询为空");
		} else {
			packData.setCode(200);
			packData.setObjs(arrangeCourseDtos);
			packData.setMsg("课程安排查询成功");
		}
		return packData;
	}

	/**
	 * 添加课程安排
	 * @param arrangeCourseDto
	 * @return
	 */
	@Override
	@Transactional
	public PackData addCourseArrange(ArrangeCourseDto arrangeCourseDto) {
		PackData packData = new PackData();
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String startDate = StringUtils.substringBefore(arrangeCourseDto.getDateScope()," - ");
			String endDate = StringUtils.substringAfter(arrangeCourseDto.getDateScope()," - ");
			arrangeCourseDto.setStartDate(simpleDateFormat.parse(startDate));
			arrangeCourseDto.setEndDate(simpleDateFormat.parse(endDate));
		} catch (ParseException e) {
			packData.setCode(400);
			packData.setMsg("日期格式转换错误");
			log.error("日期格式转换错误:"+e);
			return packData;
		}

		Integer re = acm.addCourseArrange(arrangeCourseDto);
		if (re != null) {
			packData.setCode(200);
			packData.setMsg("添加成功");
		} else {
			packData.setCode(400);
			packData.setMsg("添加失败");
			log.error("添加失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		return packData;
	}

	/**
	 * 删除课程安排
	 * @param courses
	 * @return
	 */
	@Override
	@Transactional
	public PackData delCourseArrange(List<Course> courses) {
		PackData packData = new PackData();
		List<Integer> ids = new ArrayList<>();

		for (Course course:courses) {
			ids.add(course.getId());
		}
		Integer re = acm.deleteCourseArrange(ids);

		if (re != null) {
			packData.setCode(200);
			packData.setMsg("删除成功");
		} else {
			packData.setCode(400);
			packData.setMsg("删除失败");
			log.error("删除失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return packData;
	}

	/**
	 * 课程安排更新
	 * @param arrangeCourseDto
	 * @return
	 */
	@Override
	@Transactional
	public PackData editCourseArrange(ArrangeCourseDto arrangeCourseDto) {
		PackData packData = new PackData();

		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String startDate = StringUtils.substringBefore(arrangeCourseDto.getDateScope()," - ");
			String endDate = StringUtils.substringAfter(arrangeCourseDto.getDateScope()," - ");
			arrangeCourseDto.setStartDate(simpleDateFormat.parse(startDate));
			arrangeCourseDto.setEndDate(simpleDateFormat.parse(endDate));
		} catch (ParseException e) {
			packData.setCode(400);
			packData.setMsg("日期格式转换错误");
			log.error("日期格式转换错误:"+e);
			return packData;
		}

		Integer re = acm.updateArrangeCourse(arrangeCourseDto);

		if (re != null) {
			packData.setCode(200);
			packData.setMsg("更新成功");
		} else {
			packData.setCode(400);
			packData.setMsg("更新失败");
			log.error("更新失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return packData;
	}


}
