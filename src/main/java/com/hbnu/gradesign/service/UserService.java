package com.hbnu.gradesign.service;

import com.hbnu.gradesign.domain.pojo.PackData;
import com.hbnu.gradesign.domain.User;

public interface UserService {
	User findUserByUsername(String username);

	PackData checkLoginUser(String username, String password);
}
