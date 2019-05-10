package com.hbnu.gradesign.controller.welcome;

import com.hbnu.gradesign.entity.pojo.WelCome;
import com.hbnu.gradesign.service.WelComeService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@Autowired
	private WelComeService ws;

	/**
	 * admin
	 * 获取首页柱状图数据
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/admin/getHistogramData",method = RequestMethod.POST)
	public WelCome getHistogramData() {
		return ws.getHistogramData();
	}

}
