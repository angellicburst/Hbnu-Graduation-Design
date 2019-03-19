package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.domain.Role;
import com.hbnu.gradesign.dao.RoleMapper;
import com.hbnu.gradesign.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper rm;

	/**
	 * 通过用户名查询用户角色
	 * @param username
	 * @return
	 */
	@Override
	public Set<String> getRolesByUsername(String username) {
		Set<String> roles = new HashSet<>();
		for (Role role : rm.getRolesByUsername(username)) {
			roles.add(role.getRole());
		}
		return roles;
	}

	/**
	 * 判断是不是超级管理员
	 * @param userId
	 * @return
	 */
	@Override
	public boolean isAdmin(Integer userId) {
		Role role = rm.getUserRole(userId);
		if (role.getId() == 1) return true;
		else return false;
	}
}
