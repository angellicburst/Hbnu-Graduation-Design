package com.hbnu.gradesign.domain.dto;

import com.hbnu.gradesign.domain.Menu;
import com.hbnu.gradesign.domain.SecMenu;

import java.util.List;

public class MenuDto extends Menu {
	/**
	 * 二级菜单
	 */
	private List<SecMenu> secMenu ;

	public List<SecMenu> getSecMenu() {
		return secMenu;
	}

	public void setSecMenu(List<SecMenu> secMenu) {
		this.secMenu = secMenu;
	}

	@Override
	public String toString() {
		return "MenuDto{" +
				"secMenu=" + secMenu +
				'}';
	}
}
