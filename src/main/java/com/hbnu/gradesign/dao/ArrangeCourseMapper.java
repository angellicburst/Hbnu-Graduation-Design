package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.entity.ArrangeCourse;
import com.hbnu.gradesign.entity.dto.ArrangeCourseDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArrangeCourseMapper {

	List<ArrangeCourseDto> getArrangeCourses(ArrangeCourse arrangeCourse);

	List<ArrangeCourseDto> getArrangeCoursesByTea(ArrangeCourseDto arrangeCourseDto);

	List<ArrangeCourseDto> getArrangeCoursesByStu(ArrangeCourseDto arrangeCourseDto);

	Integer addCourseArrange(ArrangeCourseDto arrangeCourseDto);

	Integer deleteCourseArrange(@Param("ids")List<Integer> ids);

	Integer updateArrangeCourse(ArrangeCourseDto arrangeCourseDto);
}