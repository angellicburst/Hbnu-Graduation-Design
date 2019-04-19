package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
	List<Role> getRolesByUsername(String username);

	Role getUserRole(Integer userId);

	List<Role> getRoles();

	Integer addRoleRelateUser(Integer userId,Integer roleId);

	Integer deleteRoleRelateUser(Integer userId,Integer roleId);
}
