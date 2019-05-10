package com.hbnu.gradesign.controller.user;

import com.hbnu.gradesign.entity.User;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.entity.pojo.PasswordInfo;
import com.hbnu.gradesign.service.RoleService;
import com.hbnu.gradesign.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

	@Autowired
	private UserService us;

	@Autowired
	private RoleService rs;

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

	/**
	 * 修改用户密码
	 * @param passwordInfo
	 * @return
	 */
	@RequestMapping(value = "/user/editPassword",method = RequestMethod.POST)
	public PackData editPassword(@RequestBody PasswordInfo passwordInfo) {
		return us.updatePassword(passwordInfo);
	}

	@RequestMapping(value = "/user/isAdmin",method = RequestMethod.POST)
	public PackData isAdmin() {
		PackData packData = new PackData();

		//Shiro subject取出用户信息
		Subject sub = SecurityUtils.getSubject();
		User user = (User) sub.getPrincipal();
		if (rs.isAdmin(user.getId())) {
			packData.setCode(200);
		} else {
			packData.setCode(400);
		}
		return packData;
	}
}
