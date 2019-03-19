package com.hbnu.gradesign.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbnu.gradesign.domain.Test;
import com.hbnu.gradesign.domain.TestData;
import com.hbnu.gradesign.domain.User;
import com.hbnu.gradesign.service.TestService;
import com.hbnu.gradesign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {

	@Autowired
	private TestService ts;

	@Autowired
	private UserService us;

	@RequestMapping("/test")
	public String test() {
		return "index";
	}

	@ResponseBody
	@RequestMapping("/user")
	public User user() {
		return us.findUserByUsername("admin");
	}


	@ResponseBody
	@RequestMapping("/json")
	public TestData<Test> JSONTest(@RequestParam(value = "page", defaultValue = "1") String pageIndex,
								   @RequestParam(value = "limit", defaultValue = "10") String pageSize) {
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		List<Test> tests = ts.test();
		PageInfo<Test> pageInfo = new PageInfo<Test>(tests);

		TestData<Test> td = new TestData<Test>();
		td.setCode(0);
		td.setMsg("成功");
		td.setCount((int) pageInfo.getTotal());
		td.setData(pageInfo.getList());
		return td;
	}
}
