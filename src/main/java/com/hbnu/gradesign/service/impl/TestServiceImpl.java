package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.entity.Test;
import com.hbnu.gradesign.dao.TestMapper;
import com.hbnu.gradesign.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
	
	@Autowired
	private TestMapper tm;
	
	@Override
	public List<Test> test() {
		System.out.println(tm);
		return tm.selectTest();
	}

	@Override
	public Integer getCount() {
		return tm.getCount();
	}
	
}
