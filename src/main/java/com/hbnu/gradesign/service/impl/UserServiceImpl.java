package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.UserMapper;
import com.hbnu.gradesign.entity.User;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
