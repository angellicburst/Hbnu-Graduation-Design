package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.DepartmentMapper;
import com.hbnu.gradesign.domain.Department;
import com.hbnu.gradesign.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentMapper dm;

	@Override
	public Department getDepartmentById(Integer id) {
		return dm.getDepartmentById(id);
	}
}
