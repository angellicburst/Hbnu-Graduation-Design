package com.hbnu.gradesign.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbnu.gradesign.bean.Test;
import com.hbnu.gradesign.dao.TestMapper;
import com.hbnu.gradesign.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	
	@Autowired
	private TestMapper tm;
	
	@Override
	public List<Test> test() {
		return tm.selectTest();
	}

	@Override
	public Integer getCount() {
		return tm.getCount();
	}
	
}
