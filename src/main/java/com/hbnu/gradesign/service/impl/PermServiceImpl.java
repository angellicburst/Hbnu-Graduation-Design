package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.service.PermService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PermServiceImpl implements PermService {
	@Override
	public Set<String> getPermsByUserId(Integer userId) {
		Set<String> perms = new HashSet<>();
		//三种编程语言代表三种角色：js程序员、java程序员、c++程序员
		//js程序员的权限
		perms.add("html:edit");
		//c++程序员的权限
		perms.add("hardware:debug");
		//java程序员的权限
		perms.add("mvn:install");
		perms.add("mvn:clean");
		perms.add("mvn:test");
		return perms;
	}
}
