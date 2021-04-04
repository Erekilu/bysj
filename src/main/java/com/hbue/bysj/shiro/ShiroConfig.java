package com.hbue.bysj.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Auther: Erekilu
 * @Date: 2021-03-24
 */
@Configuration
public class ShiroConfig
{
	/**
	 * 创建ShiroFilterFactoryBean
	 */
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager)
	{
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 添加Shiro内置过滤器，设置拦截规则
		Map<String, String> filterMap = new LinkedHashMap<>();
		// 公开页面
		filterMap.put("/login", "anon");
		filterMap.put("/error", "anon");
		filterMap.put("/views/regist.html", "anon");
		filterMap.put("/views/toLogin.html", "anon");

		// 权限拦截规则
		filterMap.put("/todayTradAmount", "perms[admin:assign]");
		filterMap.put("/todayTrad", "perms[admin:assign]");
		filterMap.put("/removeTrad", "perms[admin:assign]");
		filterMap.put("/addTrad", "perms[admin:assign]");
		filterMap.put("/getTrads", "perms[admin:assign]");
		filterMap.put("/todayBorrow", "perms[admin:assign]");
		filterMap.put("/changeBorrowState", "perms[admin:assign]");
		filterMap.put("/removeBorrow", "perms[admin:assign]");
		filterMap.put("/addBorrow", "perms[admin:assign]");
		filterMap.put("/getBorrows", "perms[admin:assign]");
		filterMap.put("/getMessage", "perms[admin:assign]");
		filterMap.put("/item_type", "perms[admin:assign]");
		filterMap.put("/views/assign/*", "perms[admin:assign]");

		filterMap.put("/addPatient", "perms[admin:record]");
		filterMap.put("/patientUpload", "perms[admin:record]");
		filterMap.put("/getData_2", "perms[admin:record]");
		filterMap.put("/views/record/*", "perms[admin:record]");

		// 认证拦截规则
		filterMap.put("/views/**", "authc");
		filterMap.put("/userChange", "authc");
		filterMap.put("/userChangePassword", "authc");
		filterMap.put("/userInfo", "authc");

		// 退出登录接口
		filterMap.put("/logout", "logout");

		// 指定登录页面
		shiroFilterFactoryBean.setLoginUrl("/views/toLogin.html");
		// 指定出错页面
		//		shiroFilterFactoryBean.setUnauthorizedUrl("/error");
		// 绑定过滤规则
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

		return shiroFilterFactoryBean;
	}

	/**
	 * 创建DefaultWebSecurityManager
	 */
	@Bean
	public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm)
	{
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		// 关联realm
		defaultWebSecurityManager.setRealm(userRealm);
		return defaultWebSecurityManager;
	}

	/**
	 * 创建Realm对象
	 */
	@Bean
	public UserRealm getAdminRealm()
	{
		return new UserRealm();
	}
}
