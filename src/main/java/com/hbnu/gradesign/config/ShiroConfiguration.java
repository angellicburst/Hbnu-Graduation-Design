package com.hbnu.gradesign.config;

import com.hbnu.gradesign.realm.ShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {
	/**
	 * 注入自定义的realm，告诉shiro如何获取用户信息来做登录或权限控制
	 */
	@Bean
	public Realm realm() {
		return new ShiroRealm();
	}


	/**
	 * springboot shiro开启注释
	 *
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor
				= new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
	 * 在@Controller注解的类的方法中加入@RequiresRole注解，会导致该方法无法映射请求，导致返回404。
	 * 加入这项配置能解决这个bug
	 */
	@Bean
	public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
		//使用setProxyTargetClass权限判断会执行两次
		//defaultAAP.setProxyTargetClass(true);
		defaultAAP.setUsePrefix(true);
		return defaultAAP;
	}

//	@Bean
//	public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//		DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
//		// 由于统一使用注解做访问控制，所以这里配置所有请求路径都可以匿名访问
//		chain.addPathDefinition("/login", "anon"); // all paths are managed via annotations
//		chain.addPathDefinition("/loginout", "anon");
//		chain.addPathDefinition("/static/**", "anon");
//		// 这另一种配置方式。但是还是用上面那种吧，容易理解一点。
//		// or allow basic authentication, but NOT require it.
//		// chainDefinition.addPathDefinition("/**", "authcBasic[permissive]");
//		chain.addPathDefinition("/**", "authc");
//		return chain;
//	}

	/**
	 * 配置shiro过滤器
	 *
	 * @param securityManager
	 * @return
	 */
	@Bean(name="shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager")SecurityManager securityManager) {
		//定义shiroFactoryBean
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		//设置securityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//设置默认登录的url
		shiroFilterFactoryBean.setLoginUrl("/login");
		//设置成功之后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		//设置未授权界面
		//shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		//LinkedHashMap是有序的，进行顺序拦截器配置
		Map<String, String> filterChainMap = new LinkedHashMap<>();
		//可以匿名访问
		filterChainMap.put("/js/**", "anon");
		filterChainMap.put("/css/**", "anon");
		filterChainMap.put("/lib/**", "anon");
		filterChainMap.put("/dologin", "anon");
		filterChainMap.put("/logout", "logout");
		//所有url必须通过认证才可以访问
		//filterChainMap.put("/**", "authc");
		//filterChainMap.put("/*", "authc");
		//filterChainMap.put("/*.*", "authc");
		//设置shiroFilterFactoryBean的FilterChainDefinitionMap
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
		return shiroFilterFactoryBean;
	}
}
