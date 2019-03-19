package com.hbnu.gradesign.service;

import com.hbnu.gradesign.entity.PackData;
import com.hbnu.gradesign.entity.User;

public interface UserService {
	User findUserByUsername(String username);

	PackData checkLoginUser(String username, String password);
}
