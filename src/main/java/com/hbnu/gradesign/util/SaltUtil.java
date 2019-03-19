package com.hbnu.gradesign.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.tomcat.util.codec.binary.Base64;

import java.security.SecureRandom;

/**
 * shiro盐值加密工具类
 */
public class SaltUtil {


	/**
	 * 利用SecureRandom随机生成盐值
	 * @return
	 */
	private static String randomSalt() {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[16];
		random.nextBytes(bytes);
		String salt = Base64.encodeBase64String(bytes);
		return salt;
	}

	/**
	 * 盐值加密
	 * @param password	密码
	 * @param hashIterations	加密次数
	 * @param type	加密类型
	 * @return
	 */
	public static String saltEncrypt(String password,Integer hashIterations,String type,String salt) {
		//[盐] 一般为用户名 或 随机数
		if (salt.equals(null) || "".equals(salt)) {
			salt = randomSalt();
		}

		System.out.println(salt);

		/*调用org.apache.shiro.crypto.hash.SimpleHash.SimpleHash(String algorithmName, Object source, Object salt, int hashIterations)
		 * 构造方法实现盐值加密  String algorithmName 为加密算法 支持md5 base64 等*/
		SimpleHash sh = new SimpleHash(type, password, ByteSource.Util.bytes(salt), hashIterations);

		System.out.println(sh.toString());
		return sh.toString();
	}
}
