package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.ExamClaMapper;
import com.hbnu.gradesign.dao.ExamMapper;
import com.hbnu.gradesign.dao.GradeMapper;
import com.hbnu.gradesign.entity.ExamCla;
import com.hbnu.gradesign.entity.Grade;
import com.hbnu.gradesign.entity.dto.ExamClaDto;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.ExamClaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class ExamClaServiceImpl implements ExamClaService {

	private static transient Log log = LogFactory.getLog(ExamClaServiceImpl.class);

	@Autowired
	private ExamClaMapper ecm;

	@Autowired
	private GradeMapper gm;

	@Autowired
	private ExamMapper em;

	/**
	 * 根据考试ID获取所有的考试班级
	 * @param examId
	 * @return
	 */
	@Override
	public PackData getExamClas(Integer examId) {
		PackData packData = new PackData();

		List<ExamClaDto> examClaDtos = ecm.getExamClas(examId);

		if (examClaDtos.isEmpty()) {
			packData.setCode(404);
			packData.setMsg("考试班级查询为空");
			log.error("考试班级查询为空");
		} else {
			packData.setCode(200);
			packData.setObjs(examClaDtos);
			packData.setMsg("考试班级查询成功");
		}
		return packData;
	}

	/**
	 * 根据考试ID为其添加班级
	 * @param examCla
	 * @return
	 */
	@Override
	@Transactional
	public PackData addExamCla(ExamCla examCla) {
		PackData packData = new PackData();

		Integer re = ecm.addExamCla(examCla);
		if (re != null) {
			List<Grade> grades = gm.getGradeDataByExam(examCla.getExamId(),examCla.getClaId());
			if (grades.isEmpty()) {
				packData.setCode(404);
				packData.setMsg("该班级下学生信息查询为空");
				return packData;
			} else {
				for (Grade grade : grades) {
					Integer reg = gm.addGradeByCla(grade);
					if (reg == null) {
						packData.setCode(400);
						packData.setMsg("添加失败");
						return packData;
					}
				}
				packData.setCode(200);
				packData.setMsg("添加成功");
			}
		} else {
			packData.setCode(400);
			packData.setMsg("添加失败");
			log.error("添加失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		return packData;
	}

	/**
	 * 根据考试ID删除其考试班级
	 * @param examCla
	 * @return
	 */
	@Override
	@Transactional
	public PackData delExamCla(ExamCla examCla) {
		PackData packData = new PackData();

		Integer re = ecm.deleteExamCla(examCla.getId());

		if (re != null) {
			Integer reg = gm.deleteGradeByCouIdClaId(em.getExam(examCla.getExamId()).getCourseId(),examCla.getClaId());
			if (reg != null) {
				packData.setCode(200);
				packData.setMsg("删除成功");
			} else {
				packData.setCode(400);
				packData.setMsg("删除失败");
				log.error("删除失败");
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		} else {
			packData.setCode(400);
			packData.setMsg("删除失败");
			log.error("删除失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return packData;
	}
}
