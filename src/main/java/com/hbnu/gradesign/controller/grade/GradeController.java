package com.hbnu.gradesign.controller.grade;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbnu.gradesign.entity.dto.GradeDto;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.GradeService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GradeController {

	@Autowired
	private GradeService gs;

	/**
	 * admin
	 * 获取某一考试中某一班级的所有学生成绩
	 * @param pageIndex
	 * @param pageSize
	 * @param courseId
	 * @param claId
	 * @return
	 */
	@RequiresRoles("admin")
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

	/**
	 * admin
	 * 获取学生ID获取该学生的所有成绩
	 * @param pageIndex
	 * @param pageSize
	 * @param studentId
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/admin/getGraByStudentId",method = RequestMethod.GET)
	public PackData getGradeByStudentId(@RequestParam(value = "page", defaultValue = "1") String pageIndex,
										@RequestParam(value = "limit", defaultValue = "10") String pageSize,
										@RequestParam(value = "studentId") String studentId) {
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		PackData packData = gs.getGradeByStu(studentId);
		PageInfo pageInfo = new PageInfo(packData.getObjs());

		packData.setCount((int) pageInfo.getTotal());

		return packData;
	}

	/**
	 * admin
	 * 成绩修改，有校验
	 * @param gradeDto
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/admin/editGrade",method = RequestMethod.POST)
	public PackData editGrade(@RequestBody GradeDto gradeDto) {
		return gs.updateGrade(gradeDto);
	}

	/**
	 * admin
	 * 成绩修改，无校验
	 * @param gradeDto
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/admin/editGradeNC",method = RequestMethod.POST)
	public PackData editGradeNoCheck(@RequestBody GradeDto gradeDto) {
		return gs.updateGradeNC(gradeDto);
	}
}
