package com.hbnu.gradesign.controller.menu;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbnu.gradesign.entity.Menu;
import com.hbnu.gradesign.entity.dto.MenuDto;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MenuController {

	@Autowired
	private MenuService ms;

	/**
	 * 获取菜单
	 *
	 * @return
	 */
	@RequestMapping(value = "/getMenu", method = RequestMethod.POST)
	public Menu getMenu(Integer id) {
		return ms.getMenu(id);
	}


	/**
	 * 获取全部菜单(分类包装)
	 *
	 * @return
	 */
	@RequestMapping(value = "/getMenus", method = RequestMethod.POST)
	public List<MenuDto> getMenus() {
		return ms.getMenusByRoleId();
	}

	/**
	 * 获取当前菜单
	 *
	 * @return
	 */
	@RequestMapping(value = "/getCurMenu", method = RequestMethod.POST)
	public MenuDto getCurMenu(Integer id, Integer level) {
		return ms.getCurMenu(id, level);
	}

	/**
	 * admin
	 * 获取全部菜单(单纯获取全部菜单)
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/menus", method = RequestMethod.GET)
	public PackData getAllMenus(@RequestParam(value = "page", defaultValue = "1") String pageIndex,
								 @RequestParam(value = "limit", defaultValue = "10") String pageSize) {
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		PackData packData = ms.getMenus();
		PageInfo pageInfo = new PageInfo(packData.getObjs());

		packData.setCount((int) pageInfo.getTotal());

		return packData;
	}

	/**
	 * admin
	 * 添加菜单
	 * @param menuDto
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/addMenu", method = RequestMethod.POST)
	public PackData addMenu(MenuDto menuDto) {
		return ms.addMenu(menuDto);
	}

	/**
	 * admin
	 * 菜单删除（多个）
	 * @param menus
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/delMenus",method = RequestMethod.POST)
	public PackData delMenus(@RequestBody List<Menu> menus) {
		return ms.delMenu(menus);
	}

	/**
	 * admin
	 * 菜单删除(单个)
	 * @param menu
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/delMenu",method = RequestMethod.POST)
	public PackData delMenu(Menu menu) {
		List<Menu> menus = new ArrayList<>();
		menus.add(menu);
		return ms.delMenu(menus);
	}

	/**
	 * admin
	 * 菜单更新
	 * @param menuDto
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/editMenu",method = RequestMethod.POST)
	public PackData editMenu(MenuDto menuDto) {
		return ms.editMenu(menuDto);
	}

}
