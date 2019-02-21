package com.hbnu.gradesign.controller;

import com.hbnu.gradesign.service.RoleService;
import org.apache.shiro.authz.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by CaiBaoHong at 2018/4/18 15:51<br>
 *     测试shiro提供的注解及功能解释
 */
@RestController
@RequestMapping("/t1")
public class ShiroController {

	@Autowired
	private RoleService rs;

	// 由于TestController类上没有加@RequiresAuthentication注解，
	// 不要求用户登录才能调用接口。所以hello()和a1()接口都是可以匿名访问的
	@GetMapping("/hello")
	public String hello() {
		return "hello spring boot";
	}

	// 游客可访问，这个有点坑，游客的意思是指：subject.getPrincipal()==null
	// 所以用户在未登录时subject.getPrincipal()==null，接口可访问
	// 而用户登录后subject.getPrincipal()！=null，接口不可访问
	@RequiresGuest
	@GetMapping("/guest")
	public String guest() {
		return "@RequiresGuest";
	}

	// 已登录用户才能访问，这个注解比@RequiresUser更严格
	// 如果用户未登录调用该接口，会抛出UnauthenticatedException
	@RequiresAuthentication
	@GetMapping("/authn")
	public String authn() {
		return "@RequiresAuthentication";
	}

	// 已登录用户或“记住我”的用户可以访问
	// 如果用户未登录或不是“记住我”的用户调用该接口，UnauthenticatedException
	@RequiresUser
	@GetMapping("/user")
	public String user() {
		return "@RequiresUser";
	}

	// 要求登录的用户具有mvn:build权限才能访问
	// 由于UserService模拟返回的用户信息中有该权限，所以这个接口可以访问
	// 如果没有登录，UnauthenticatedException
	@RequiresPermissions("add")
	@GetMapping("/add")
	public String mvnInstall() {
		return "add";
	}

	// 要求登录的用户具有mvn:build权限才能访问
	// 由于UserService模拟返回的用户信息中【没有】该权限，所以这个接口【不可以】访问
	// 如果没有登录，UnauthenticatedException
	// 如果登录了，但是没有这个权限，会报错UnauthorizedException
	@RequiresPermissions("acdemic")
	@GetMapping("/acdemic")
	public String gradleBuild() {
		return "acdemic";
	}

	// 要求登录的用户具有js角色才能访问
	// 由于UserService模拟返回的用户信息中有该角色，所以这个接口可访问
	// 如果没有登录，UnauthenticatedException
	@RequiresRoles("teacher")
	@GetMapping("/teacher")
	public String js() {
		return "js teacher";
	}

	// 要求登录的用户具有python角色才能访问
	// 由于UserService模拟返回的用户信息中有该角色，所以这个接口可访问
	// 如果没有登录，UnauthenticatedException
	// 如果登录了，但是没有该角色，会抛出UnauthorizedException
	@RequiresRoles("student")
	@GetMapping("/student")
	public String python() {
		return "student";
	}

//	/**
//	 * 登陆
//	 * @param username 用户名
//	 * @param password 密码
//	 */
//	@GetMapping(value = "/login")
//	public String login(String username, String password) {
//		// 从SecurityUtils里边创建一个 subject
//		Subject subject = SecurityUtils.getSubject();
//		// 在认证提交前准备 token（令牌）
//		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//		// 执行认证登陆
//		subject.login(token);
//		//根据权限，指定返回数据
//		boolean role = rs.getRolesByUserId(1).contains("java");
//
//		if (role == true) {
//			return "success";
//		} else {
//			return "fail";
//		}
//	}
//
//	/**
//	 * 退出登录
//	 * @return
//	 */
//	@RequestMapping("/logout")
//	public String logout() {
//		Subject subject = SecurityUtils.getSubject();
//		subject.logout();
//		return "logout success";
//	}
}
