package com.hbnu.gradesign.controller.user;

import com.hbnu.gradesign.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	/**
	 * 页面右上角显示用户信息
	 * @return
	 */
	@PostMapping(value = "/showUser")
	public User showUser() {
		//Shiro subject取出用户信息
		Subject sub = SecurityUtils.getSubject();
		User user = (User) sub.getPrincipal();
		return user;
	}
}
