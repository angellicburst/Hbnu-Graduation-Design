package com.hbnu.gradesign.service.impl;

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
	public Test test() {
		return tm.selectTest();
	}
	
}
