package com.hbnu.gradesign.service;

import com.hbnu.gradesign.entity.ArrangeCourse;
import com.hbnu.gradesign.entity.Course;
import com.hbnu.gradesign.entity.dto.ArrangeCourseDto;
import com.hbnu.gradesign.entity.pojo.PackData;

import java.util.List;

public interface ArrangeCourseService {

	PackData getArrangeCourses(ArrangeCourse arrangeCourse);

	PackData addCourseArrange(ArrangeCourseDto arrangeCourseDto);

	PackData delCourseArrange(List<Course> courses);

	PackData editCourseArrange(ArrangeCourseDto arrangeCourseDto);
}
