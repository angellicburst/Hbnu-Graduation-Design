package com.hbnu.gradesign.service;

import com.hbnu.gradesign.entity.User;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.entity.pojo.PasswordInfo;

public interface UserService {
	User findUserByUsername(String username);

	User findUserById(Integer userId);

	PackData updateUser(User user);

	PackData updatePassword(PasswordInfo passwordInfo);
}
