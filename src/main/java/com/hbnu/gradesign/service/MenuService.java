package com.hbnu.gradesign.service;

import com.hbnu.gradesign.entity.Menu;
import com.hbnu.gradesign.entity.dto.MenuDto;
import com.hbnu.gradesign.entity.pojo.PackData;

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
