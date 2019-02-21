package com.hbnu.gradesign;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaltMD5 {

	//   J/ms7qTJtqmysekuY8/v1TAS+VKqXdH5sB7ulXZOWho=
	//e7dde25f4413f2e505b4e5c77803625e
//6c590e9dcf14a0cd4d693a79a812f87bdd3a121db54d86ebb39de75894db1482
	@Test
	public void salt() {
		//所需加密的参数  即  密码
		String source = "123456";
		//[盐] 一般为用户名 或 随机数
		String salt = "wxKYXuTPST5SG0jMQzVPsg==";
		//加密次数
		int hashIterations = 1024;

		//调用 org.apache.shiro.crypto.hash.Md5Hash.Md5Hash(Object source, Object salt, int hashIterations)构造方法实现MD5盐值加密
		Md5Hash mh = new Md5Hash(source, salt, hashIterations);
		//打印最终结果
		System.out.println(mh.toString());


		/*调用org.apache.shiro.crypto.hash.SimpleHash.SimpleHash(String algorithmName, Object source, Object salt, int hashIterations)
		 * 构造方法实现盐值加密  String algorithmName 为加密算法 支持md5 base64 等*/
		SimpleHash sh = new SimpleHash("md5", source, ByteSource.Util.bytes(salt), hashIterations);

		//打印最终结果
		System.out.println(sh.toString());
	}

}
