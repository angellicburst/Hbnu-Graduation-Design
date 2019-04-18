package com.hbnu.gradesign.controller.major;

import com.hbnu.gradesign.entity.Major;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MajorController {

	@Autowired
	private MajorService ms;

	/**
	 * 获取所有专业
	 * @return
	 */
	@RequestMapping(value = "/getMajors",method = RequestMethod.POST)
	public PackData getMajors() {
		return ms.getMajors();
	}

	/**
	 * 关系查询专业
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "/getMajorsByDep",method = RequestMethod.POST)
	public List<Major> getMajorsByDep(Integer departmentId) {
		return ms.getMajorsByDepartmentId(departmentId);
	}
}
