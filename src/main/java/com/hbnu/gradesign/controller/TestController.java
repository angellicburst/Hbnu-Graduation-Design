package com.hbnu.gradesign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hbnu.gradesign.service.TestService;

@Controller
public class TestController {
	
	@Autowired
	private TestService ts;
	
	@RequestMapping("/test")
	public String test() {
		System.out.println(ts.test().getTest());
		return "test";
	}
}
