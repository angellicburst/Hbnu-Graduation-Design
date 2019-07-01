package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.UserMapper;
import com.hbnu.gradesign.entity.User;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.entity.pojo.PasswordInfo;
import com.hbnu.gradesign.service.UserService;
import com.hbnu.gradesign.util.SaltUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class UserServiceImpl implements UserService {

	private static transient Log log = LogFactory.getLog(UserServiceImpl.class);

	@Autowired
	private UserMapper um;

	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	@Override
	public User findUserByUsername(String username) {
		return um.findUserByUsername(username);
	}

	/**
	 * 根据用户ID查找用户
	 * @param userId
	 * @return
	 */
	@Override
	public User findUserById(Integer userId) {
		return um.findUserById(userId);
	}

	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	@Override
	public PackData updateUser(User user) {
		PackData packData = new PackData();

		Integer re = um.updateUser(user);

		if (re > 0) {
			packData.setCode(200);
			packData.setMsg("用户更新成功");
		} else {
			packData.setCode(400);
			packData.setMsg("用户更新失败");
			log.error("用户更新失败");
			//手动事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		return packData;
	}

	/**
	 * 修改密码
	 * @param passwordInfo
	 * @return
	 */
	@Override
	@Transactional
	public PackData updatePassword(PasswordInfo passwordInfo) {
		PackData packData = new PackData();

		//获取登陆用户
		Subject sub = SecurityUtils.getSubject();
		User user = (User) sub.getPrincipal();

		if (!SaltUtil.saltEncrypt(passwordInfo.getOldPassWord(),1024,"md5",user.getSalt()).equals(user.getPassword())) {
			log.error("原密码输入错误");
			packData.setMsg("原密码输入错误");
			packData.setCode(400);
			return packData;
		} else {
			//设置新的盐值对新的密码进行加密
			user.setSalt(SaltUtil.randomSalt());
			user.setPassword(SaltUtil.saltEncrypt(passwordInfo.getNewPassWord(),1024,"md5",user.getSalt()));

			Integer re = um.updateUser(user);

			if (re > 0) {
				packData.setCode(200);
				packData.setMsg("用户密码修改成功");
			} else {
				packData.setCode(400);
				packData.setMsg("用户密码修改失败");
				log.error("用户密码修改失败");
				//手动事务回滚
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

		}
		return packData;
	}
}
