package com.hbnu.gradesign.realm;

import com.hbnu.gradesign.entity.User;
import com.hbnu.gradesign.service.RoleService;
import com.hbnu.gradesign.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * 这个类是参照JDBCRealm写的，主要是自定义了如何查询用户信息，如何查询用户的角色和权限，如何校验密码等逻辑
 */
public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	/**
	 * 告诉shiro如何根据获取到的用户信息中的密码和盐值来校验密码
	 */
	{
		//设置用于匹配密码的CredentialsMatcher
		HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
		//hashMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
		//设置加密方式
		hashMatcher.setHashAlgorithmName("md5");
		//hashMatcher.setStoredCredentialsHexEncoded(false);
		//设置加密次数
		hashMatcher.setHashIterations(1024);
		this.setCredentialsMatcher(hashMatcher);
	}


	/**
	 * 定义如何获取用户的角色和权限的逻辑，给shiro做权限判断
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//null username are invalid
		if (principals == null) {
			throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
		}

		User user = (User) getAvailablePrincipal(principals);
		System.out.println(user);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		System.out.println("获取角色信息："+roleService.getRolesByUsername(user.getUsername()));
		//System.out.println("获取权限信息："+permService.getPermsByUserId(user.getId()));
		info.setRoles(roleService.getRolesByUsername(user.getUsername()));
		//info.setStringPermissions(permService.getPermsByUserId(user.getId()));
		return info;
	}

	/**
	 * 定义如何获取用户信息的业务逻辑，给shiro做登录
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();

		System.out.println(username);

		// Null username is invalid
		if (username == null) {
			throw new AccountException("Null usernames are not allowed by this realm.");
		}

		User userDB = userService.findUserByUsername(username);

		System.out.println(userDB);

		if (userDB == null) {
			throw new UnknownAccountException("No account found for admin [" + username + "]");
		}

		//查询用户的角色和权限存到SimpleAuthenticationInfo中，这样在其它地方
		//SecurityUtils.getSubject().getPrincipal()就能拿出用户的所有信息，包括角色和权限
		Set<String> roles = roleService.getRolesByUsername(userDB.getUsername());
		//Set<String> perms = permService.getPermsByUserId(userDB.getId());
		roleService.getRolesByUsername(userDB.getUsername()).addAll(roles);
		//permService.getPermsByUserId(userDB.getId()).addAll(perms);

		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userDB, userDB.getPassword(), getName());

		if (userDB.getSalt() != null) {
			info.setCredentialsSalt(ByteSource.Util.bytes(userDB.getSalt()));
		}

		return info;

	}

	/**
	 * 设置超级管理员可以访问全部的接口
	 * @param principals
	 * @param permission
	 * @return
	 */
	@Override
	public  boolean isPermitted(PrincipalCollection principals, String permission){
		User user = (User) principals.getPrimaryPrincipal();
		return roleService.isAdmin(user.getId())||super.isPermitted(principals,permission);
	}
	@Override
	public boolean hasRole(PrincipalCollection principals, String roleIdentifier) {
		User user = (User) principals.getPrimaryPrincipal();
		return roleService.isAdmin(user.getId())||super.hasRole(principals,roleIdentifier);
	}

}
