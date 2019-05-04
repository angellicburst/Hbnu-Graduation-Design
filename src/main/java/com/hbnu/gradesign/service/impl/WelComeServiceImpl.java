package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.ExamMapper;
import com.hbnu.gradesign.dao.StudentMapper;
import com.hbnu.gradesign.dao.TeacherMapper;
import com.hbnu.gradesign.entity.pojo.WelCome;
import com.hbnu.gradesign.service.WelComeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WelComeServiceImpl implements WelComeService {

	@Autowired
	private StudentMapper sm;

	@Autowired
	private ExamMapper em;

	@Autowired
	private TeacherMapper ts;

	/**
	 * 获取首页柱状图数据
	 * 根据学生获取年
	 * 按年获取对应学生数量
	 * 按年获取对应考试数量
	 * @return
	 */
	@Override
	public WelCome getHistogramData() {
		WelCome welCome = new WelCome();

		welCome.setYears(sm.getStuYear());
		welCome.setStuCouByYea(sm.getStuCountGroupByYear());
		welCome.setExamCouByYea(em.getExamCountGroupByYear());
		welCome.setStuCounts(sm.getStudentCount());
		welCome.setTeaCounts(ts.getTeacherCount());
		welCome.setExamCounts(em.getExamCount());
		return welCome;
	}

}
