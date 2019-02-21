package com.hbnu.gradesign.controller.user;

import com.hbnu.gradesign.service.RoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	/**
	 * 登陆
	 * @param username 用户名
	 * @param password 密码
	 */
	@GetMapping(value = "/dologin")
	public String dologin(String username, String password) {
		// 从SecurityUtils里边创建一个 subject
		Subject subject = SecurityUtils.getSubject();
		// 在认证提交前准备 token（令牌）
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		System.out.println(token);
		// 执行认证登陆
		subject.login(token);
		//根据权限，指定返回数据
//		boolean role = roleService.getRolesByUsername(username).contains("admin");
//		System.out.println(role);

//		if (role == true) {
			return "success";
//		} else {
//			return "fail";
//		}
	}

	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "logout success";
	}

	@RequiresRoles("admin")
	@RequestMapping("/asd")
	public String asd() {
		return "asd";
	}

	@RequiresPermissions("all")
	@RequestMapping("/zxc")
	public String zxc() {
		return "zxc";
	}

}
