package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.entity.Role;
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
	
	@Override
	public Set<String> getRolesByUsername(String username) {
		Set<String> roles = new HashSet<>();
		for (Role role : rm.getRolesByUsername(username)) {
			roles.add(role.getRole());
		}
		return roles;
	}
}
