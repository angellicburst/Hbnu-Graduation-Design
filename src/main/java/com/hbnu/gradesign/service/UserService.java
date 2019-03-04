package com.hbnu.gradesign.service;

import com.hbnu.gradesign.entity.User;

public interface UserService {
	User findUserByUsername(String Username);
}
