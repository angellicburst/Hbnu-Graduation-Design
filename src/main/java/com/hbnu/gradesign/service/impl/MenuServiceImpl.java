package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.MenuMapper;
import com.hbnu.gradesign.entity.Menu;
import com.hbnu.gradesign.entity.dto.MenuDto;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.properties.PathProperties;
import com.hbnu.gradesign.service.MenuService;
import com.hbnu.gradesign.util.FileUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

	private static transient Log log = LogFactory.getLog(MenuServiceImpl.class);

	@Autowired
	private MenuMapper mm;

	@Autowired
	private PathProperties pathProperties;

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
			log.error("菜单查询为空");
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
	@Override
	@Transactional
	public PackData addMenu(MenuDto menuDto) {
		PackData packData = null;
		//限制只有一级菜单才能添加图片
		if (menuDto.getLevel() == 1) {
			packData = FileUtil.uploadFile(menuDto.getImgFile(), pathProperties.getMenuImgSavePath());
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
				log.error("添加失败");
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
				packData = FileUtil.delFile(m.getImg(), pathProperties.getMenuImgSavePath());
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
			log.error("删除失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
			packData = FileUtil.delFile(menuDto.getImg(), pathProperties.getMenuImgSavePath());
			if (packData.getCode() == 200) {
				packData = FileUtil.uploadFile(menuDto.getImgFile(), pathProperties.getMenuImgSavePath());
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
			log.error("更新失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return packData;
	}


}
