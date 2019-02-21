package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.entity.User;

public interface UserMapper {
	User findUserByUsername(String username);
}
