package com.hbnu.gradesign.controller.menu;

import com.hbnu.gradesign.domain.Menu;
import com.hbnu.gradesign.domain.dto.MenuDto;
import com.hbnu.gradesign.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {

	@Autowired
	private MenuService ms;

	/**
	 * 获取菜单
	 * @return
	 */
	@RequestMapping(value = "/getMenu",method = RequestMethod.POST)
	public Menu getMenu(Integer id) {
		return ms.getMenu(id);
	}


	/**
	 * 获取全部菜单
	 * @return
	 */
	@RequestMapping(value = "/getMenus",method = RequestMethod.POST)
	public List<MenuDto> getMenus() {
		return ms.getMenus(1);
	}

	/**
	 * 获取当前菜单
	 * @return
	 */
	@RequestMapping(value = "/getCurMenu",method = RequestMethod.POST)
	public MenuDto getCurMenu(Integer id,Integer level) {
		return ms.getCurMenu(id,level);
	}

}
