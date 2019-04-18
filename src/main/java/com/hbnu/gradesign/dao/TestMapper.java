package com.hbnu.gradesign.dao;

import java.util.List;

import com.hbnu.gradesign.entity.Test;
import org.springframework.stereotype.Repository;

@Repository
public interface TestMapper {
	List<Test> selectTest();
	
	Integer getCount();
}
