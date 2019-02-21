package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.entity.Permission;
import com.hbnu.gradesign.dao.PermMapper;
import com.hbnu.gradesign.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PermServiceImpl implements PermService {

	@Autowired
	private PermMapper pm;

	@Override
	public Set<String> getPermsByUserId(Integer userId) {
		Set<String> perms = new HashSet<>();
		//三种编程语言代表三种角色：js程序员、java程序员、c++程序员
		for (Permission permission : pm.getPermsByUserId(userId)) {
			perms.add(permission.getPermission());
		}
		return perms;
	}
}
