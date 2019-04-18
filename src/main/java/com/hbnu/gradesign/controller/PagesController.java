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

	/**
	 * admin
	 * 跳转菜单管理页面
	 * @return
	 */
	@RequestMapping(value = "/menu/manage")
	public String menuManagement() {
		return "menu/menuManage";
	}

	/**
	 * admin
	 * 跳转学生管理页面
	 * @return
	 */
	@RequestMapping(value = "/admin/stuManage")
	public String stuManageByAdm() {
		return "student/stuManageAdm";
	}

	/**
	 * teacher
	 * 跳转学生管理页面
	 * @return
	 */
	@RequestMapping(value = "/teacher/stuManage")
	public String stuManageByTea() {
		return "student/stuManageTea";
	}

}
