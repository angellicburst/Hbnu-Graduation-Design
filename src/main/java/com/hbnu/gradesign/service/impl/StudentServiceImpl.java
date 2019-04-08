package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.StudentMapper;
import com.hbnu.gradesign.domain.dto.StudentDto;
import com.hbnu.gradesign.domain.pojo.PackData;
import com.hbnu.gradesign.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper sm;

	@Override
	public PackData getStusAdm() {
		PackData packData = new PackData();
		List<StudentDto> studentDtos = sm.getStudentsAdm();
		if (studentDtos.isEmpty()) {
			packData.setCode(400);
			packData.setMsg("学生查询失败");
		} else {
			packData.setCode(200);
			packData.setObjs(studentDtos);
			packData.setMsg("学生查询成功");
		}
		return packData;
	}
}
