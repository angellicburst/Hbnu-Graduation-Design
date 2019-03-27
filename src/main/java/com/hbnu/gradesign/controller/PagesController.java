package com.hbnu.gradesign.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {

	/**
	 * 跳转登陆页
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	/**
	 * 跳转主页
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
}
