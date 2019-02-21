package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.entity.Role;

import java.util.List;

public interface RoleMapper {
	List<Role> getRolesByUsername(String username);
}
