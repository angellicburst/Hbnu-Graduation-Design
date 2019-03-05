package com.hbnu.gradesign;

import com.hbnu.gradesign.util.SaltUtil;
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

//	eefe4bcee417ae082fb3e7bf04b78bef
//	KtcIXLbJ19rYhkT6E2OI3A==
	@Test
	public void salt() {
		//所需加密的参数  即  密码
		String source = "123456";
		//[盐] 一般为用户名 或 随机数
		String salt = "KtcIXLbJ19rYhkT6E2OI3A==";
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

	@Test
	public void randomSalt() {
		SaltUtil.saltEncrypt("123456",1024,"md5");
	}

}
