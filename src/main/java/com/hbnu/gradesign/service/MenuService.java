package com.hbnu.gradesign.service;

import com.hbnu.gradesign.domain.dto.MenuDto;

import java.util.List;

public interface MenuService {
	public List<MenuDto> getMenus(Integer roleId);
}
