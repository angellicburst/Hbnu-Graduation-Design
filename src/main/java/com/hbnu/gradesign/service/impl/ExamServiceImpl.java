package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.ExamClaMapper;
import com.hbnu.gradesign.dao.ExamMapper;
import com.hbnu.gradesign.dao.GradeMapper;
import com.hbnu.gradesign.entity.Exam;
import com.hbnu.gradesign.entity.dto.ExamDto;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.ExamService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.thymeleaf.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

	private static transient Log log = LogFactory.getLog(ExamServiceImpl.class);

	@Autowired
	private ExamMapper em;

	@Autowired
	private ExamClaMapper ecm;

	@Autowired
	private GradeMapper gm;

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

	/**
	 * 根据教师ID获取所有的考试信息
	 * @param examDto
	 * @return
	 */
	@Override
	public PackData getExamsByTeacherId(ExamDto examDto) {
		PackData packData = new PackData();

		List<ExamDto> examDtos = em.getExamsByTeacherId(examDto);
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

	/**
	 * 获取所有已经结束的考试信息
	 * @param examDto
	 * @return
	 */
	@Override
	public PackData getEndExamsAdm(ExamDto examDto) {
		PackData packData = new PackData();

		List<ExamDto> examDtos = em.getEndExams(examDto);
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

	/**
	 * 添加考试
	 * @param examDto
	 * @return
	 */
	@Override
	@Transactional
	public PackData addExam(ExamDto examDto) {
		PackData packData = new PackData();
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String startTime = StringUtils.substringBefore(examDto.getTimeScope()," - ");
			String endTime = StringUtils.substringAfter(examDto.getTimeScope()," - ");
			examDto.setStartTime(simpleDateFormat.parse(startTime));
			examDto.setEndTime(simpleDateFormat.parse(endTime));

			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			examDto.setCreateDate(simpleDateFormat.parse(simpleDateFormat.format(new Date())) );
		} catch (ParseException e) {
			packData.setCode(400);
			packData.setMsg("日期格式转换错误");
			log.error("日期格式转换错误:"+e);
			return packData;
		}

		//设置默认状态值
		examDto.setStatus(0);

		Integer re = em.addExam(examDto);
		if (re != null) {
			packData.setCode(200);
			packData.setMsg("添加成功");
		} else {
			packData.setCode(400);
			packData.setMsg("添加失败");
			log.error("添加失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		return packData;
	}

	/**
	 * 删除考试
	 * @param exams
	 * @return
	 */
	@Override
	@Transactional
	public PackData delExam(List<Exam> exams) {
		PackData packData = new PackData();

		Integer rec = ecm.deleteExamClaByExamId(exams.get(0).getId());
		if (rec != null) {
			List<Integer> ids = new ArrayList<>();

			for (Exam exam : exams) {
				ids.add(exam.getId());
			}
			Integer re = em.deleteExam(ids);

			if (re != null) {
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

	/**
	 * 更新考试
	 * @param examDto
	 * @return
	 */
	@Override
	@Transactional
	public PackData editExam(ExamDto examDto) {
		PackData packData = new PackData();
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String startTime = StringUtils.substringBefore(examDto.getTimeScope()," - ");
			String endTime = StringUtils.substringAfter(examDto.getTimeScope()," - ");
			examDto.setStartTime(simpleDateFormat.parse(startTime));
			examDto.setEndTime(simpleDateFormat.parse(endTime));
		} catch (ParseException e) {
			packData.setCode(400);
			packData.setMsg("日期格式转换错误");
			log.error("日期格式转换错误:"+e);
			return packData;
		}

		Integer re = em.updateExam(examDto);

		if (re != null) {
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
