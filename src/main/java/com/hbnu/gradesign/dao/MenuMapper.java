package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.entity.Menu;
import com.hbnu.gradesign.entity.dto.MenuDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {
	Menu getMenu(Integer id);

	List<MenuDto> getMenuByRoleId(Integer roleId);

	MenuDto getCurMenuById(Integer id ,Integer sec_id);

	List<Menu> getMenus();

	Integer addMenu(MenuDto menuDto);

	Integer delMenu(@Param("ids")List<Integer> ids);

	Integer editMenu(MenuDto menuDto);
}