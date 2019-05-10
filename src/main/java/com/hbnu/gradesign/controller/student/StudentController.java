package com.hbnu.gradesign.controller.student;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbnu.gradesign.entity.Student;
import com.hbnu.gradesign.entity.User;
import com.hbnu.gradesign.entity.dto.StudentDto;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.StudentService;
import com.hbnu.gradesign.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

	private static transient Log log = LogFactory.getLog(StudentController.class);

	@Autowired
	private StudentService ss;

	@Autowired
	private UserService us;

	/**
	 * admin
	 * 获取所有学生
	 * @param pageIndex
	 * @param pageSize
	 * @param studentDto
	 * @return
	 */
	@RequestMapping(value = "/admin/getStudents",method = RequestMethod.GET)
	public PackData getStuAdmin(@RequestParam(value = "page", defaultValue = "1") String pageIndex,
								@RequestParam(value = "limit", defaultValue = "10") String pageSize,
								StudentDto studentDto) {
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		PackData packData = ss.getStusAdm(studentDto);
		PageInfo pageInfo = new PageInfo(packData.getObjs());

		packData.setCount((int) pageInfo.getTotal());

		return packData;
	}

	/**
	 * admin
	 * 获取所有学生
	 * 用于成绩管理
	 * @param pageIndex
	 * @param pageSize
	 * @param studentDto
	 * @return
	 */
	@RequestMapping(value = "/admin/getStuGrades",method = RequestMethod.GET)
	public PackData getStuToGradeAdmin(@RequestParam(value = "page", defaultValue = "1") String pageIndex,
								@RequestParam(value = "limit", defaultValue = "10") String pageSize,
								StudentDto studentDto) {
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		PackData packData = ss.getStuToGrade(studentDto);
		PageInfo pageInfo = new PageInfo(packData.getObjs());

		packData.setCount((int) pageInfo.getTotal());

		return packData;
	}

	/**
	 * 批量导入学生模板下载下载
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/admin/student/template/download",method = RequestMethod.GET)
	public PackData templateDownLoad(HttpServletResponse response) throws UnsupportedEncodingException {
		return ss.templateDownLoad(response);
	}

	/**
	 * excel批量添加学生
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/admin/addStudent",method = RequestMethod.POST)
	public PackData addStudent(@RequestParam("file") MultipartFile file) throws Exception {
		return ss.addStudentsByExcel(file);
	}

	/**
	 * 学生批量删除
	 * @param students
	 * @return
	 */
	@RequestMapping(value = "/admin/delStudents",method = RequestMethod.POST)
	public PackData delStudents(@RequestBody List<Student> students) {
		return ss.delStudent(students);
	}

	/**
	 * 学生删除
	 * @param student
	 * @return
	 */
	@RequestMapping(value = "/admin/delStudent",method = RequestMethod.POST)
	public PackData delStudent(@RequestBody Student student) {
		List<Student> students = new ArrayList<>();
		students.add(student);
		return ss.delStudent(students);
	}

	/**
	 * 学生状态变更
	 * 0：启用
	 * 1：停用
	 * @param student
	 * @return
	 */
	@RequestMapping(value = "/admin/student/changeStatus",method = RequestMethod.POST)
	public PackData changeStatus(@RequestBody Student student) {
		User user = null;
		PackData packData = null;
		try {
			user = us.findUserById(student.getUserId());
		} catch (Exception e) {
			packData = new PackData();
			packData.setCode(404);
			packData.setMsg("学生对应用户为空");
			log.error("学生对应用户为空"+e);
			return packData;
		}

		if (student.getStatus() == 0) {
			student.setStatus(1);
			user.setStatus(1);
		} else if (student.getStatus() == 1) {
			student.setStatus(0);
			user.setStatus(0);
		}

		packData = us.updateUser(user);

		if (packData.getCode() == 200) {
			packData = ss.updateStudent(student);
		}

		return packData;
	}

	/**
	 * 更新学生
	 * @param student
	 * @return
	 */
	@RequestMapping(value = "/admin/editStudent",method = RequestMethod.POST)
	public PackData editStudent(@RequestBody Student student) {
		return ss.updateStudent(student);
	}

}
