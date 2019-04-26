package com.hbnu.gradesign.service.impl;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.hbnu.gradesign.dao.RoleMapper;
import com.hbnu.gradesign.dao.TeacherMapper;
import com.hbnu.gradesign.dao.UserMapper;
import com.hbnu.gradesign.entity.User;
import com.hbnu.gradesign.entity.dto.TeacherDto;
import com.hbnu.gradesign.entity.excel.TeacherExcel;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.properties.PathProperties;
import com.hbnu.gradesign.service.TeacherService;
import com.hbnu.gradesign.util.EasyExcelUtil;
import com.hbnu.gradesign.util.FileUtil;
import com.hbnu.gradesign.util.SaltUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

	private static transient Log log = LogFactory.getLog(TeacherServiceImpl.class);

	@Autowired
	private TeacherMapper tm;

	@Autowired
	private UserMapper um;

	@Autowired
	private RoleMapper rm;

	@Autowired
	private PathProperties pathProperties;

	@Override
	public PackData getTeachersAdm(TeacherDto teacherDto) {
		PackData packData = new PackData();

		List<TeacherDto> teacherDtos = tm.selectTeachers(teacherDto);

		if (teacherDtos.isEmpty()) {
			packData.setCode(400);
			packData.setMsg("教师查询失败");
			log.error("教师查询失败");
		} else {
			packData.setCode(200);
			packData.setMsg("教师查询成功");
			packData.setObjs(teacherDtos);
		}

		return packData;
	}

	@Override
	public PackData templateDownLoad(HttpServletResponse response) throws UnsupportedEncodingException {
		return FileUtil.downloadFile(response,pathProperties.getTeaTemSavePath());
	}

	@Override
	public PackData addTeachersByExcel(MultipartFile file) throws Exception {
		List<Object> teacherObjs = EasyExcelUtil.readExcelWithModel(file.getInputStream(), TeacherExcel.class, ExcelTypeEnum.XLS);
		List<TeacherExcel> teachers = (List) teacherObjs;
		PackData packData = new PackData();

		for (TeacherExcel teacher: teachers) {

			User user = new User();
			user.setUsername(teacher.getId());
			user.setStatus(0);
			user.setSalt(SaltUtil.randomSalt());
			user.setPassword(SaltUtil.saltEncrypt("123456",1024,"md5",user.getSalt()));
			//1.添加用户
			Integer reu = um.addUser(user);
			if (reu > 0) {
				//2.设置角色
				Integer rer = rm.addRoleRelateUser(user.getId(),2);
				if (rer > 0) {
					//3.先设置教师user_id，然后添加教师
					teacher.setUserId(user.getId());
					Integer res = tm.addTeachers(teacher);
					if (res > 0) {
						packData.setCode(200);
						packData.setMsg("添加成功");
					} else {
						packData.setCode(400);
						packData.setMsg("教师添加失败");
						log.error("教师添加失败");
						//手动事务回滚
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					}
				} else {
					packData.setCode(400);
					packData.setMsg("教师关联的用户角色添加失败");
					log.error("教师关联的用户角色添加失败");
					//手动事务回滚
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				}
			} else {
				packData.setCode(400);
				packData.setMsg("教师关联的用户添加失败");
				log.error("教师关联的用户添加失败");
				//手动事务回滚
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		}

		return packData;
	}
}
