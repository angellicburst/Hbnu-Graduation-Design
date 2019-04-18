package com.hbnu.gradesign.controller.login;

import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.RoleService;
import com.hbnu.gradesign.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class LoginController {

	private static transient Log log = LogFactory.getLog(LoginController.class);

	@Autowired
	private RoleService rs;

	@Autowired
	private UserService us;

	/**
	 * 登陆
	 *
	 * @param username 用户名
	 * @param password 密码
	 */
	@GetMapping(value = "/dologin")
	public PackData dologin(String username, String password) {
		PackData packData = new PackData();

		// 从SecurityUtils里边创建一个 subject
		Subject subject = SecurityUtils.getSubject();

		try {
			// 在认证提交前准备 token（令牌）
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			// 执行认证登陆
			subject.login(token);
		} catch (AuthenticationException e) {
			log.error("用户名或密码错误：" + e.getMessage());
			packData.setCode(500);
			packData.setMsg("用户名或密码错误");
			return packData;
		}

		//根据权限，指定返回数据
		Set<String> role = rs.getRolesByUsername(username);

		packData.setObj(SecurityUtils.getSubject().getPrincipal());
		packData.setCode(200);
		packData.setMsg("登陆成功");

		//存储用户角色
		Map<String, String> params = new HashMap<>();
		params.put("role", role.iterator().next());
		packData.setParams(params);
		return packData;
	}
}
