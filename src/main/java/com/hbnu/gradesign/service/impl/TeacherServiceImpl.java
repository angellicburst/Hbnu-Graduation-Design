package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.TeacherMapper;
import com.hbnu.gradesign.entity.dto.TeacherDto;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.properties.PathProperties;
import com.hbnu.gradesign.service.TeacherService;
import com.hbnu.gradesign.util.FileUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

	private static transient Log log = LogFactory.getLog(TeacherServiceImpl.class);

	@Autowired
	private TeacherMapper tm;

	@Autowired
	private PathProperties pathProperties;

	@Override
	public PackData getTeachersAdm(TeacherDto teacherDto) {
		PackData packData = new PackData();

		List<TeacherDto> teacherDtos = tm.selectTeachers(teacherDto);

		if (teacherDtos.isEmpty()) {
			packData.setCode(400);
			packData.setMsg("教师查询失败");
			log.error("教师查询失败");
		} else {
			packData.setCode(200);
			packData.setMsg("教师查询成功");
			packData.setObjs(teacherDtos);
		}

		return packData;
	}

	@Override
	public PackData templateDownLoad(HttpServletResponse response) throws UnsupportedEncodingException {
		return FileUtil.downloadFile(response,pathProperties.getTeaTemSavePath());
	}
}
