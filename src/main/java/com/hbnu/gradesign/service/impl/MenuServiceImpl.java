package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.MenuMapper;
import com.hbnu.gradesign.domain.Menu;
import com.hbnu.gradesign.domain.dto.MenuDto;
import com.hbnu.gradesign.domain.pojo.PackData;
import com.hbnu.gradesign.service.MenuService;
import com.hbnu.gradesign.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper mm;

	/**
	 * 根据ID获取某个菜单
	 * @param id
	 * @return
	 */
	@Override
	public Menu getMenu(Integer id) {
		return mm.getMenu(id);
	}

	/**
	 * 获取所有菜单
	 * @return
	 */
	@Override
	public PackData getMenus() {
		PackData packData = new PackData();
		List<Menu> menus = mm.getMenus();
		if (menus.isEmpty()) {
			packData.setCode(404);
			packData.setMsg("菜单查询为空");
		} else {
			packData.setCode(200);
			packData.setObjs(menus);
			packData.setMsg("菜单查询成功");
		}
		return packData;
	}

	/**
	 * 根据角色获取菜单
	 * @param roleId
	 * @return
	 */
	@Override
	public List<MenuDto> getMenusByRoleId(Integer roleId) {
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

	/**
	 * 菜单添加
	 * @param menuDto
	 * @return
	 */
	@Transactional
	@Override
	public PackData addMenu(MenuDto menuDto) {
		PackData packData = null;
		//限制只有一级菜单才能添加图片
		if (menuDto.getLevel() == 1) {
			packData = UploadUtil.uploadFile(menuDto.getImgFile());
			menuDto.setImg(packData.getObj().toString());
		} else if (menuDto.getLevel() == 2){
			packData = new PackData();
			packData.setCode(200);
		}

		if (packData.getCode() == 200) {
			Integer re = mm.addMenu(menuDto);
			if (re != null) {
				packData.setMsg("添加成功");
			} else {
				packData.setCode(400);
				packData.setMsg("添加失败");
			}
		}
		return packData;
	}

	/**
	 * 菜单删除
	 * @param menus
	 * @return
	 */
	@Transactional
	@Override
	public PackData delMenu(List<Menu> menus) {
		PackData packData = new PackData();
		List<Integer> ids = new ArrayList<>();
		for (Menu m:menus) {
			ids.add(m.getId());
			if (m.getLevel() == 1) {
				packData = UploadUtil.delFile(m.getImg());
				if (packData.getCode() != 200) {
					return packData;
				}
			}
		}
		Integer re = mm.delMenu(ids);

		if (re != null) {
			packData.setCode(200);
			packData.setMsg("删除成功");
		} else {
			packData.setCode(400);
			packData.setMsg("删除失败");
		}
		return packData;
	}

	/**
	 * 菜单更新
	 * @param menuDto
	 * @return
	 */
	@Transactional
	@Override
	public PackData editMenu(MenuDto menuDto) {
		PackData packData;
		if (!menuDto.getImgFile().isEmpty()) {
			packData = UploadUtil.delFile(menuDto.getImg());
			if (packData.getCode() == 200) {
				packData = UploadUtil.uploadFile(menuDto.getImgFile());
				menuDto.setImg(menuDto.getImgFile().getOriginalFilename());
				if (packData.getCode() != 200) {
					return packData;
				}
			} else {
				return packData;
			}
		} else {
			packData = new PackData();
		}

		Integer re = mm.editMenu(menuDto);

		if (re != null) {
			packData.setCode(200);
			packData.setMsg("更新成功");
		} else {
			packData.setCode(400);
			packData.setMsg("更新失败");
		}
		return packData;
	}


}
