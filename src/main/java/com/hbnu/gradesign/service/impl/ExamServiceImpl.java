package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.ExamMapper;
import com.hbnu.gradesign.entity.dto.ExamDto;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.ExamService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

	private static transient Log log = LogFactory.getLog(ExamServiceImpl.class);

	@Autowired
	private ExamMapper em;

	/**
	 * 获取所有的考试信息
	 * @param examDto
	 * @return
	 */
	@Override
	public PackData getExamsAdm(ExamDto examDto) {
		PackData packData = new PackData();

		List<ExamDto> examDtos = em.getExams(examDto);
		if (examDtos.isEmpty()) {
			packData.setCode(404);
			packData.setMsg("考试查询为空");
			log.error("考试查询为空");
		} else {
			packData.setCode(200);
			packData.setObjs(examDtos);
			packData.setMsg("考试查询成功");
		}
		return packData;
	}
}
