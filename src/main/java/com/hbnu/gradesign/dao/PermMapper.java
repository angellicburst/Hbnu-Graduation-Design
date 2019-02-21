package com.hbnu.gradesign.dao;


import com.hbnu.gradesign.entity.Permission;

import java.util.List;

public interface PermMapper {
	List<Permission> getPermsByUserId(Integer userId);
}
