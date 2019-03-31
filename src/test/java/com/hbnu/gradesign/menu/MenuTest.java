package com.hbnu.gradesign.menu;


import com.hbnu.gradesign.domain.dto.MenuDto;
import com.hbnu.gradesign.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuTest {

	@Autowired
	private MenuService ms;

	@Test
	public void getMenu() {
		for (MenuDto m:ms.getMenus(1)
			 ) {
			System.out.println(m.getMenuName());
			System.out.println(m);
		}
	}

	@Test
	public void getMenuByRoleId() {
		MenuDto m = ms.getCurMenu(5,1);
		System.out.println(m.getMenuName());
		System.out.println(m);
	}
}
