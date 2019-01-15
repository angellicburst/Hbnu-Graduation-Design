package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.bean.User;
import com.hbnu.gradesign.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
	@Override
	public User findUserByUsername(String Username) {
		String uname = "fyc";
		User user = new User();
		user.setUname(uname);
		user.setNick(uname+"NICK");
		user.setPwd("J/ms7qTJtqmysekuY8/v1TAS+VKqXdH5sB7ulXZOWho=");//密码明文是123456
		user.setSalt("wxKYXuTPST5SG0jMQzVPsg==");//加密密码的盐值
		user.setUid(new Random().nextInt());//随机分配一个id
		user.setCreated(new Date());
		return user;
	}
}
