package com.hbnu.gradesign.service;

import com.hbnu.gradesign.domain.Menu;
import com.hbnu.gradesign.domain.dto.MenuDto;

import java.util.List;

public interface MenuService {
	Menu getMenu(Integer id);

	List<MenuDto> getMenus(Integer roleId);

	MenuDto getCurMenu(Integer id,Integer level);
}
