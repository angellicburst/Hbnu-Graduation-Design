package com.hbnu.gradesign.controller.exam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbnu.gradesign.entity.Exam;
import com.hbnu.gradesign.entity.dto.ExamDto;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.ExamService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ExamController {

	@Autowired
	private ExamService es;

	/**
	 * admin
	 * 获取所有的考试
	 * @param pageIndex
	 * @param pageSize
	 * @param examDto
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/admin/getExams",method = RequestMethod.GET)
	public PackData getExamsAdmin(@RequestParam(value = "page", defaultValue = "1") String pageIndex,
								@RequestParam(value = "limit", defaultValue = "10") String pageSize,
								ExamDto examDto) {
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		PackData packData = es.getExamsAdm(examDto);
		PageInfo pageInfo = new PageInfo(packData.getObjs());

		packData.setCount((int) pageInfo.getTotal());

		return packData;
	}

	/**
	 * admin
	 * 获取所有已经结束的考试
	 * @param pageIndex
	 * @param pageSize
	 * @param examDto
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/admin/getEndExams",method = RequestMethod.GET)
	public PackData getEndExamsAdmin(@RequestParam(value = "page", defaultValue = "1") String pageIndex,
								  @RequestParam(value = "limit", defaultValue = "10") String pageSize,
								  ExamDto examDto) {
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		PackData packData = es.getEndExamsAdm(examDto);
		PageInfo pageInfo = new PageInfo(packData.getObjs());

		packData.setCount((int) pageInfo.getTotal());

		return packData;
	}

	/**
	 * admin
	 * 添加考试
	 * @param examDto
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/admin/addExam",method = RequestMethod.POST)
	public PackData addExam(ExamDto examDto) {
		return es.addExam(examDto);
	}

	/**
	 * admin
	 * 删除考试
	 * @param exams
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/admin/delExams",method = RequestMethod.POST)
	public PackData delExams(@RequestBody List<Exam> exams) {
		return es.delExam(exams);
	}

	/**
	 * admin
	 * 删除考试
	 * @param exam
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/admin/delExam",method = RequestMethod.POST)
	public PackData delExam(@RequestBody Exam exam) {
		List<Exam> exams = new ArrayList<>();
		exams.add(exam);
		return es.delExam(exams);
	}

	/**
	 * admin
	 * 更新考试
	 * @param examDto
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/admin/editExam",method = RequestMethod.POST)
	public PackData editExam(@RequestBody ExamDto examDto) {
		return es.editExam(examDto);
	}

}
