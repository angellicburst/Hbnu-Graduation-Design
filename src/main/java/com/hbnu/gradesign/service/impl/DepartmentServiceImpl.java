package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.DepartmentMapper;
import com.hbnu.gradesign.entity.Department;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.DepartmentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private static transient Log log = LogFactory.getLog(DepartmentServiceImpl.class);

	@Autowired
	private DepartmentMapper dm;

	@Override
	public Department getDepartmentById(Integer id) {
		return dm.getDepartmentById(id);
	}

	@Override
	@Cacheable(cacheNames = "departments")
	public PackData getDepartments() {
		PackData packData = new PackData();
		List<Department> departments = dm.getDepartments();
		if (departments.isEmpty()) {
			packData.setCode(404);
			packData.setMsg("院系查询为空");
			log.error("院系查询为空");
		} else {
			packData.setCode(200);
			packData.setObjs(departments);
			packData.setMsg("院系查询成功");
		}
		return packData;
	}
}
