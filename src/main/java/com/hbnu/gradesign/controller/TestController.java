package com.hbnu.gradesign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbnu.gradesign.bean.Test;
import com.hbnu.gradesign.bean.TestData;
import com.hbnu.gradesign.service.TestService;

@Controller
public class TestController {
	
	@Autowired
	private TestService ts;
	
	@RequestMapping("/test")
	public String test() {
		for (Test t : ts.test()) {
			System.out.println(t);
		}
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("/json")
	public TestData<Test> JSONTest() {
		TestData<Test> td = new TestData<Test>();
		td.setCode(0);
		td.setMsg("成功");
		td.setCount(ts.getCount());
		td.setData(ts.test());
		return td;
	}
}
