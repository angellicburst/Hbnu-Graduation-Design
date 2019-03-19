package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.domain.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermMapper {
	List<Permission> getPermsByUserId(Integer userId);
}
