package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
	List<Role> getRolesByUsername(String username);
	Role getUserRole(Integer userId);
}
