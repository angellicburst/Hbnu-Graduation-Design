package com.hbnu.gradesign.service;

import com.hbnu.gradesign.domain.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
	Set<String> getRolesByUsername(String username);

	boolean isAdmin(Integer userId);

	List<Role> getRoles();
}
