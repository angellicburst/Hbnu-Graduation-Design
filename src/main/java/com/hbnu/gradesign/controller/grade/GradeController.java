package com.hbnu.gradesign.controller.grade;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbnu.gradesign.entity.Grade;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GradeController {

	@Autowired
	private GradeService gs;

	/**
	 * 获取某一考试中某一班级的所有学生成绩
	 * @param pageIndex
	 * @param pageSize
	 * @param courseId
	 * @param claId
	 * @return
	 */
	@RequestMapping(value = "/admin/getGradesExamCla",method = RequestMethod.GET)
	public PackData getGradesByExamIdAndClaIdAdmin(@RequestParam(value = "page", defaultValue = "1") String pageIndex,
								  @RequestParam(value = "limit", defaultValue = "10") String pageSize,
								  @RequestParam(value = "courseId") Integer courseId, @RequestParam(value = "claId") Integer claId) {
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		PackData packData = gs.getGraByExmIdAClaId(courseId,claId);
		PageInfo pageInfo = new PageInfo(packData.getObjs());

		packData.setCount((int) pageInfo.getTotal());

		return packData;
	}

	@RequestMapping(value = "/admin/editGrade",method = RequestMethod.POST)
	public PackData editGrade(@RequestBody Grade grade) {
		return gs.updateGrade(grade);
	}
}
