package com.hbnu.gradesign.entity.dto;

import com.hbnu.gradesign.entity.Menu;
import com.hbnu.gradesign.entity.SecMenu;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class MenuDto extends Menu {
	/**
	 * 二级菜单
	 */
	private List<SecMenu> secMenu ;

	/**
	 * 图片文件
	 */
	private MultipartFile imgFile;

}
