package com.hbnu.gradesign.service.impl;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.hbnu.gradesign.dao.RoleMapper;
import com.hbnu.gradesign.dao.StudentMapper;
import com.hbnu.gradesign.dao.UserMapper;
import com.hbnu.gradesign.entity.Student;
import com.hbnu.gradesign.entity.User;
import com.hbnu.gradesign.entity.dto.StudentDto;
import com.hbnu.gradesign.entity.excel.StudentExcel;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.properties.PathProperties;
import com.hbnu.gradesign.service.StudentService;
import com.hbnu.gradesign.util.EasyExcelUtil;
import com.hbnu.gradesign.util.FileUtil;
import com.hbnu.gradesign.util.SaltUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper sm;

	@Autowired
	private UserMapper um;

	@Autowired
	private RoleMapper rm;

	@Autowired
	private PathProperties pathProperties;

	/**
	 * 获取学生列表
	 *
	 * @param studentDto
	 * @return
	 */
	@Override
	public PackData getStusAdm(StudentDto studentDto) {
		PackData packData = new PackData();

		List<StudentDto> studentDtos = sm.getStudentsAdm(studentDto);
		if (studentDtos.isEmpty()) {
			packData.setCode(404);
			packData.setMsg("学生查询为空");
		} else {
			packData.setCode(200);
			packData.setObjs(studentDtos);
			packData.setMsg("学生查询成功");
		}
		return packData;
	}

	/**
	 * excel批量添加学生
	 * 先添加用户
	 * @param file
	 * @return
	 */
	@Override
	@Transactional
	public PackData addStudentsByExcel(MultipartFile file) throws IOException {
		List<Object> studentObjs = EasyExcelUtil.readExcelWithModel(file.getInputStream(), StudentExcel.class, ExcelTypeEnum.XLS);
		List<StudentExcel> students = (List) studentObjs;
		PackData packData = new PackData();

		for (StudentExcel student: students) {

			User user = new User();
			user.setUsername(student.getId());
			user.setStatus(0);
			user.setSalt(SaltUtil.randomSalt());
			user.setPassword(SaltUtil.saltEncrypt("123456",1024,"md5",user.getSalt()));
			//1.添加用户
			Integer reu = um.addUser(user);
			if (reu > 0) {
				//2.设置角色
				Integer rer = rm.addRoleRelateUser(user.getId(),3);
				if (rer > 0) {
					//3.先设置学生user_id，然后添加学生
					student.setUserId(user.getId());
					Integer res = sm.addStudent(student);
					if (res > 0) {
						packData.setCode(200);
						packData.setMsg("添加成功");
					} else {
						packData.setCode(400);
						packData.setMsg("学生添加失败");
						return packData;
					}
				} else {
					packData.setCode(400);
					packData.setMsg("学生关联的用户角色添加失败");
					return packData;
				}
			} else {
				packData.setCode(400);
				packData.setMsg("学生关联的用户添加失败");
				return packData;
			}

		}

		return packData;
	}

	/**
	 * 学生导入模板下载
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@Override
	public PackData templateDownLoad(HttpServletResponse response) throws UnsupportedEncodingException {
		return FileUtil.downloadFile(response,pathProperties.getStuTemSavePath());
	}

	@Override
	@Transactional
	public PackData delStudent(List<Student> students) {
		PackData packData = new PackData();

		for (Student student: students) {
			Integer res = sm.deleteStudent(student.getId());

			if (res > 0) {
				Integer rer = rm.deleteRoleRelateUser(student.getUserId(),3);
				if (rer > 0) {
					Integer reu = um.deleteUser(student.getUserId());
					if (reu > 0) {
						packData.setCode(200);
						packData.setMsg("删除成功");
					} else {
						packData.setCode(400);
						packData.setMsg("学生对应用户删除失败");
					}

				} else {
					packData.setCode(400);
					packData.setMsg("学生对应角色关系删除失败");
				}
			} else {
				packData.setCode(400);
				packData.setMsg("学生删除失败");
			}
		}

		return packData;
	}

}
