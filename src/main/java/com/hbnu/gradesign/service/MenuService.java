package com.hbnu.gradesign.service;

import com.hbnu.gradesign.domain.Menu;
import com.hbnu.gradesign.domain.dto.MenuDto;
import com.hbnu.gradesign.domain.pojo.PackData;

import java.util.List;

public interface MenuService {
	Menu getMenu(Integer id);

	PackData getMenus();

	List<MenuDto> getMenusByRoleId(Integer roleId);

	MenuDto getCurMenu(Integer id,Integer level);

	PackData addMenu(MenuDto menuDto);

	PackData delMenu(List<Menu> menus);

	PackData editMenu(MenuDto menuDto);
}
