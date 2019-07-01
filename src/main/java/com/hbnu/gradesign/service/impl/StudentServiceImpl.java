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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

	private static transient Log log = LogFactory.getLog(StudentServiceImpl.class);

	@Autowired
	private StudentMapper sm;

	@Autowired
	private UserMapper um;

	@Autowired
	private RoleMapper rm;

	@Autowired
	private PathProperties pathProperties;

	/**
	 * admin
	 * 获取学生列表
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
			log.error("学生查询为空");
		} else {
			packData.setCode(200);
			packData.setObjs(studentDtos);
			packData.setMsg("学生查询成功");
		}
		return packData;
	}

	/**
	 * teacher
	 * 根据teacherID获取该教师所带的所有学生
	 * @param studentDto
	 * @return
	 */
	@Override
	public PackData getStusTea(StudentDto studentDto) {
		PackData packData = new PackData();

		List<StudentDto> studentDtos = sm.getStudentsTea(studentDto);
		if (studentDtos.isEmpty()) {
			packData.setCode(404);
			packData.setMsg("学生查询为空");
			log.error("学生查询为空");
		} else {
			packData.setCode(200);
			packData.setObjs(studentDtos);
			packData.setMsg("学生查询成功");
		}
		return packData;
	}

	/**
	 * 获取学生列表
	 * 用于成绩管理
	 * @param studentDto
	 * @return
	 */
	@Override
	public PackData getStuToGrade(StudentDto studentDto) {
		PackData packData = new PackData();

		List<StudentDto> studentDtos = sm.getStudentsToGradeAdm(studentDto);
		if (studentDtos.isEmpty()) {
			packData.setCode(404);
			packData.setMsg("学生查询为空");
			log.error("学生查询为空");
		} else {
			packData.setCode(200);
			packData.setObjs(studentDtos);
			packData.setMsg("学生查询成功");
		}
		return packData;
	}

	/**
	 * 根据user_id获取学生
	 * @param userId
	 * @return
	 */
	@Override
	public Student getStuByUserId(Integer userId) {
		return sm.getStudentByUserId(userId);
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
						log.error("学生添加失败");
						//手动事务回滚
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					}
				} else {
					packData.setCode(400);
					packData.setMsg("学生关联的用户角色添加失败");
					log.error("学生关联的用户角色添加失败");
					//手动事务回滚
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				}
			} else {
				packData.setCode(400);
				packData.setMsg("学生关联的用户添加失败");
				log.error("学生关联的用户添加失败");
				//手动事务回滚
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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

	/**
	 * 学生删除
	 * @param students
	 * @return
	 */
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
						log.error("学生对应用户删除失败");
						//手动事务回滚
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					}

				} else {
					packData.setCode(400);
					packData.setMsg("学生对应角色关系删除失败");
					log.error("学生对应角色关系删除失败");
					//手动事务回滚
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				}
			} else {
				packData.setCode(400);
				packData.setMsg("学生删除失败");
				log.error("学生删除失败");
				//手动事务回滚
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		}

		return packData;
	}

	/**
	 * 更新学生信息
	 * @param student
	 * @return
	 */
	@Override
	@Transactional
	public PackData updateStudent(Student student) {
		PackData packData = new PackData();
		Integer re = sm.updateStudent(student);
		if (re > 0) {
			packData.setCode(200);
			packData.setMsg("学生更新成功");
		} else {
			packData.setCode(400);
			packData.setMsg("学生更新失败");
			log.error("学生更新失败");
			//手动事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return packData;
	}

}
