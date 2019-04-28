package com.hbnu.gradesign.controller.page;

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
	 * admin
	 * 跳转教师管理页面
	 * @return
	 */
	@RequestMapping(value = "/admin/teaManage")
	public String teaManageByAdm() {
		return "teacher/teaManageAdm";
	}

	/**
	 * admin
	 * 跳转课程管理页面
	 * @return
	 */
	@RequestMapping(value = "/admin/couManage")
	public String couManageByAdm() {
		return "course/couManageAdm";
	}

	@RequestMapping(value = "/admin/examManage")
	public String examManageByAdm() {
		return "exam/examManage";
	}
}
