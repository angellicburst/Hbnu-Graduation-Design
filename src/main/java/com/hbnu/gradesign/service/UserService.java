package com.hbnu.gradesign.service;

import com.hbnu.gradesign.bean.User;

public interface UserService {
	User findUserByUsername(String Username);
}
