package com.hbnu.gradesign.controller.department;

import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

	@Autowired
	private DepartmentService ds;

	/**
	 * 获取所有的院系
	 * @return
	 */
	@RequestMapping(value = "/getDepartments",method = RequestMethod.POST)
	public PackData getDepartments() {
		return ds.getDepartments();
	}

}
