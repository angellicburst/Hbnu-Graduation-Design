package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.ExamMapper;
import com.hbnu.gradesign.dao.GradeMapper;
import com.hbnu.gradesign.entity.Grade;
import com.hbnu.gradesign.entity.dto.GradeDto;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.GradeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

	private static transient Log log = LogFactory.getLog(GradeServiceImpl.class);

	@Autowired
	private GradeMapper gm;

	@Autowired
	private ExamMapper em;

	/**
	 * 根据考试ID和班级ID查询所有的学生的考试成绩
	 * @param courseId
	 * @param claId
	 * @return
	 */
	@Override
	public PackData getGraByExmIdAClaId(Integer courseId, Integer claId) {
		PackData packData = new PackData();

		List<GradeDto> gradeDtos = gm.getGradesByExamIdAndClaId(courseId,claId);

		if (gradeDtos.isEmpty()) {
			packData.setCode(404);
			packData.setMsg("学生成绩查询为空");
			log.error("学生成绩查询为空");
		} else {
			packData.setCode(200);
			packData.setObjs(gradeDtos);
			packData.setMsg("学生成绩查询成功");
		}
		return packData;
	}

	@Override
	@Transactional
	public PackData updateGrade(Grade grade) {

		PackData packData = new PackData();

		Integer re = gm.updateGrade(grade);

		if (re != null) {
			if (gm.getNumGradeIsNull() == 0) {

			}
			packData.setCode(200);
			packData.setMsg("更新成功");
		} else {
			packData.setCode(400);
			packData.setMsg("更新失败");
			log.error("更新失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return packData;
	}
}
