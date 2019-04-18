package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.UserMapper;
import com.hbnu.gradesign.entity.User;
import com.hbnu.gradesign.service.UserService;
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
		return um.findUserByUsername(username);
	}
}
