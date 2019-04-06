package com.hbnu.gradesign.domain.dto;

import com.hbnu.gradesign.domain.Menu;
import com.hbnu.gradesign.domain.SecMenu;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class MenuDto extends Menu {
	/**
	 * 二级菜单
	 */
	private List<SecMenu> secMenu ;

	/**
	 * 图片文件
	 */
	private MultipartFile imgFile;

	public List<SecMenu> getSecMenu() {
		return secMenu;
	}

	public void setSecMenu(List<SecMenu> secMenu) {
		this.secMenu = secMenu;
	}

	public MultipartFile getImgFile() {
		return imgFile;
	}

	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}
}
