package com.hbnu.gradesign.controller.teacher;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbnu.gradesign.entity.Teacher;
import com.hbnu.gradesign.entity.User;
import com.hbnu.gradesign.entity.dto.TeacherDto;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.TeacherService;
import com.hbnu.gradesign.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TeacherController {

	private static transient Log log = LogFactory.getLog(TeacherController.class);

	@Autowired
	private TeacherService ts;

	@Autowired
	private UserService us;

	/**
	 * admin
	 * 教师列表
	 * @param pageIndex
	 * @param pageSize
	 * @param teacherDto
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/admin/getTeachers",method = RequestMethod.GET)
	public PackData getTeaAdmin(@RequestParam(value = "page", defaultValue = "1") String pageIndex,
								@RequestParam(value = "limit", defaultValue = "10") String pageSize,
								TeacherDto teacherDto) {
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		PackData packData = ts.getTeachersAdm(teacherDto);
		PageInfo pageInfo = new PageInfo(packData.getObjs());

		packData.setCount((int) pageInfo.getTotal());

		return packData;
	}

	/**
	 * admin
	 * 教师批量导入模板下载
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/admin/teacher/template/download",method = RequestMethod.GET)
	public PackData templateDownLoad(HttpServletResponse response) throws UnsupportedEncodingException {
		return ts.templateDownLoad(response);
	}

	/**
	 * admin
	 * 批量导入教师
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/admin/addTeachers",method = RequestMethod.POST)
	public PackData addTeachers(@RequestParam("file") MultipartFile file) throws Exception {
		return ts.addTeachersByExcel(file);
	}

	/**
	 * admin
	 * 批量删除
	 * @param teachers
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/admin/delTeachers",method = RequestMethod.POST)
	public PackData delStudents(@RequestBody List<Teacher> teachers) {
		return ts.delTeacher(teachers);
	}

	/**
	 * admin
	 * 删除教师
	 * @param teacher
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/admin/delTeacher",method = RequestMethod.POST)
	public PackData delStudent(@RequestBody Teacher teacher) {
		List<Teacher> teachers = new ArrayList<>();
		teachers.add(teacher);
		return ts.delTeacher(teachers);
	}

	/**
	 * admin
	 * 状态变更
	 * @param teacher
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/admin/teacher/changeStatus",method = RequestMethod.POST)
	public PackData changeStatus(@RequestBody Teacher teacher) {
		User user = null;
		PackData packData = null;
		try {
			user = us.findUserById(teacher.getUserId());
		} catch (Exception e) {
			packData = new PackData();
			packData.setCode(404);
			packData.setMsg("教师对应用户为空");
			log.error("教师对应用户为空"+e);
			return packData;
		}

		if (teacher.getStatus() == 0) {
			teacher.setStatus(1);
			user.setStatus(1);
		} else if (teacher.getStatus() == 1) {
			teacher.setStatus(0);
			user.setStatus(0);
		}

		packData = us.updateUser(user);

		if (packData.getCode() == 200) {
			packData = ts.updateTeacher(teacher);
		}

		return packData;
	}

	/**
	 * admin
	 * 教师信息更新
	 * @param teacher
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/admin/editTeacher",method = RequestMethod.POST)
	public PackData editStudent(@RequestBody Teacher teacher) {
		return ts.updateTeacher(teacher);
	}

	/**
	 * 获取所有的教师
	 * @return
	 */
	@RequestMapping(value = "/getAllTeachers",method = RequestMethod.POST)
	public PackData getAllTeachers() {
		return ts.getAllTeachers();
	}

}
