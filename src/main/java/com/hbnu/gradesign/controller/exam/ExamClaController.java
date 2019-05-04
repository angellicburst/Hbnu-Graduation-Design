package com.hbnu.gradesign.controller.exam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbnu.gradesign.entity.ExamCla;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.ExamClaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExamClaController {

	@Autowired
	private ExamClaService ecs;

	/**
	 * 获取某一考试的所有班级
	 * @param pageIndex
	 * @param pageSize
	 * @param examId
	 * @return
	 */
	@RequestMapping(value = "/admin/getExamClas",method = RequestMethod.GET)
	public PackData getExamsAdmin(@RequestParam(value = "page", defaultValue = "1") String pageIndex,
								  @RequestParam(value = "limit", defaultValue = "10") String pageSize,
								  @RequestParam(value = "examId") Integer examId) {
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		PackData packData = ecs.getExamClas(examId);
		PageInfo pageInfo = new PageInfo(packData.getObjs());

		packData.setCount((int) pageInfo.getTotal());

		return packData;
	}

	/**
	 * 为某一考试添加班级
	 * @param examCla
	 * @return
	 */
	@RequestMapping(value = "/admin/addExamCla",method = RequestMethod.POST)
	public PackData addExam(ExamCla examCla) {
		return ecs.addExamCla(examCla);
	}


	/**
	 * 删除考试
	 * @param examCla
	 * @return
	 */
	@RequestMapping(value = "/admin/delExamCla",method = RequestMethod.POST)
	public PackData delExam(ExamCla examCla) {
		return ecs.delExamCla(examCla.getId());
	}
}
