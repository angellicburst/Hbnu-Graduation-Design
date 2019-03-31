package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.MenuMapper;
import com.hbnu.gradesign.domain.Menu;
import com.hbnu.gradesign.domain.dto.MenuDto;
import com.hbnu.gradesign.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper mm;

	@Override
	public Menu getMenu(Integer id) {
		return mm.getMenu(id);
	}

	/**
	 * 根据角色获取菜单
	 * @param roleId
	 * @return
	 */
	@Override
	@Cacheable(cacheNames= "menuDto",key="#roleId")
	public List<MenuDto> getMenus(Integer roleId) {
		return mm.getMenuByRoleId(roleId);
	}

	/**
	 * 获得当前所在菜单
	 * @param id
	 * @param level
	 * @return
	 */
	@Override
	public MenuDto getCurMenu(Integer id,Integer level) {
		if (level == 1) {
			return mm.getCurMenuById(id,null);
		} else {
			return mm.getCurMenuById(null,id);
		}
	}


}
