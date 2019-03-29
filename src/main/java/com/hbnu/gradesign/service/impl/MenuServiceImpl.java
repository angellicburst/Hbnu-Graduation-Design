package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.MenuMapper;
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
}
