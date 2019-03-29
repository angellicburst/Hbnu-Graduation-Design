package com.hbnu.gradesign.controller.menu;

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

	@RequestMapping(value = "/getMenus",method = RequestMethod.POST)
	public List<MenuDto> getMenus() {
		return ms.getMenus(1);
	}
}
