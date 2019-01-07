package com.hbnu.gradesign.dao;

import java.util.List;

import com.hbnu.gradesign.bean.Test;

public interface TestMapper {
	List<Test> selectTest();
	
	Integer getCount();
}
