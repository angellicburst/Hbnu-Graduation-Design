package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMapper {
	Course getCourseById(Integer id);

	List<Course> getCourses();
}