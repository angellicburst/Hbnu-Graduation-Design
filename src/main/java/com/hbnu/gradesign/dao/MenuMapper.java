package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.domain.dto.MenuDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {
	List<MenuDto> getMenuByRoleId(Integer roleId);
}