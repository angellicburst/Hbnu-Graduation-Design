package com.hbnu.gradesign.controller.user;

import com.hbnu.gradesign.entity.User;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.entity.pojo.PasswordInfo;
import com.hbnu.gradesign.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

	@Autowired
	private UserService us;

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

	@RequestMapping(value = "/user/editPassword",method = RequestMethod.POST)
	public PackData editPassword(@RequestBody PasswordInfo passwordInfo) {
		return us.updatePassword(passwordInfo);
	}
}
