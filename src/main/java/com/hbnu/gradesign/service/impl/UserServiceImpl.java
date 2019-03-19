package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.UserMapper;
import com.hbnu.gradesign.entity.PackData;
import com.hbnu.gradesign.entity.User;
import com.hbnu.gradesign.service.UserService;
import com.hbnu.gradesign.util.SaltUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper um;

	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	@Override
	public User findUserByUsername(String username) {
		System.out.println(um);
		return um.findUserByUsername(username);
	}

	/**
	 * 登录用户校验
	 * @param username
	 * @param password
	 * @return
	 */
	@Override
	public PackData checkLoginUser(String username, String password) {
		PackData packData = new PackData();

		String saltpwd = SaltUtil.saltEncrypt(password,1024,"md5",um.findUserByUsername(username).getSalt());
		User user = um.checkUser(username,saltpwd);

		//Shiro已经校验了用户名和密码，此时无需再次校验
		packData.setRecode(200);
		packData.setRemsg("登陆成功");
		packData.setReobj(user);

		return packData;
	}
}
