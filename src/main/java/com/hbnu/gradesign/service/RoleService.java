package com.hbnu.gradesign.service;

import java.util.Set;

public interface RoleService {
	Set<String> getRolesByUsername(String username);
}
