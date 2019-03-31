package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.domain.Menu;
import com.hbnu.gradesign.domain.dto.MenuDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {
	Menu getMenu(Integer id);

	List<MenuDto> getMenuByRoleId(Integer roleId);

	MenuDto getCurMenuById(Integer id ,Integer sec_id);
}