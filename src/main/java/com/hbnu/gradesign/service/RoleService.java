package com.hbnu.gradesign.service;

import com.hbnu.gradesign.entity.Role;

import java.util.Set;

public interface RoleService {
	Set<String> getRolesByUsername(String username);

	boolean isAdmin(Integer userId);
}
