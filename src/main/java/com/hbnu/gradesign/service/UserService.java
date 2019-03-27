package com.hbnu.gradesign.service;

import com.hbnu.gradesign.domain.User;

public interface UserService {
	User findUserByUsername(String username);
}
