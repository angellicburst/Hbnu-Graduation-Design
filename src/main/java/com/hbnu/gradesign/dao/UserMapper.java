package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
	User findUserByUsername(String username);

	User checkUser(String username, String password);
}
