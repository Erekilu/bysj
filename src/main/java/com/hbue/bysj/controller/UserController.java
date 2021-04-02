package com.hbue.bysj.controller;

import com.hbue.bysj.constant.user;
import com.hbue.bysj.domain.User;
import com.hbue.bysj.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Erekilu
 * @Date: 2021-03-24
 */
@Controller
@RestController
public class UserController
{
	@Autowired
	private UserService userService;

	/**
	 * 更改当前登录用户信息
	 * @return 处理状态码
	 */
	@PostMapping("/userChange")
	public Map<String, Object> userChange(User user)
	{
		Map<String, Object> map = new HashMap<>();
		Subject subject = SecurityUtils.getSubject();
		User sessionUser = (User)subject.getPrincipal();
		// 如果当前用户还未登录，直接返回
		if (sessionUser == null) {
			return null;
		}

		// 将sessionUser中的用户id赋值给表单中的user
		user.setId(sessionUser.getId());
		// 执行修改信息操作
		User result = userService.changeUserById(user);
		// 维护shiro中的user对象，这里不可能出问题，所以没加异常判断
		if (result != null)
		{
			sessionUser.setUserNickname(result.getUserNickname());
			sessionUser.setUserSex(result.getUserSex());
			sessionUser.setUserUpdateTime(result.getUserUpdateTime());
			map.put("code", 0);
		}
		return map;
	}

	/**
	 * 修改当前登录者的密码
	 * @return 处理结果和处理信息（如：密码错误，请重新输入）
	 */
	@PostMapping("/userChangePassword")
	public Map<String, Object> userChangePassword(String oldPassword, String password)
	{
		Map<String, Object> map = new HashMap<>();
		User user = new User();
		Subject subject = SecurityUtils.getSubject();
		User sessionUser = (User)subject.getPrincipal();
		// 如果当前用户还未登录，直接返回
		if (sessionUser == null) {
			return null;
		}

		// 判空
		int code = 0;
		String msg = "";
		if (password != null && oldPassword != null)
		{
			// 获取数据库中的密码
			String realPassword = userService.findUserByUsername(sessionUser.getUsername()).getPassword();
			// 如果旧密码不对
			if (!realPassword.equals(oldPassword))
			{
				code = -1;
				msg = "密码错误，请重新输入";
			}
			// 旧密码正确
			else
			{
				// 将sessionUser中的用户id赋值给空user
				user.setId(sessionUser.getId());
				// 将新密码赋值给空user
				user.setPassword(password);
				// 执行修改密码操作
				User result = userService.changeUserById(user);
				// 维护sessionUser的信息
				sessionUser.setUserUpdateTime(result.getUserUpdateTime());
			}
		}
		else
		{
			code = -2;
			msg = "密码不能为空";
		}

		map.put("code", code);
		map.put("msg", msg);
		return map;
	}

	/**
	 * 获取登录用户信息
	 * @return 登录者的所有信息
	 */
	@GetMapping("/userInfo")
	public Map<String, Object> userInfo()
	{
		Map<String, Object> map = new HashMap<>();
		Subject subject = SecurityUtils.getSubject();
		User user = (User)subject.getPrincipal();

		// 登录者的密码不会包装
		if (user != null)
		{
			user.setPassword("");
			map.put("user", user);
			map.put("code", 0);
		}
		else
		{
			map.put("code", -1);
			map.put("msg", "获取用户信息失败");
		}

		return map;
	}

	/**
	 * 注册用户
	 * @param username
	 * @param password
	 * @return
	 */
	@PostMapping("/regist")
	public Map<String, Object> regist(String username, String password, String nickname)
	{
		Map<String, Object> map = new HashMap<>();
		// 检测用户名是否重复
		User existUser = userService.findUserByUsername(username);
		if (existUser != null) {
			map.put("code", 2);
			map.put("msg", "用户名重复");
			return map;
		}

		// 添加用户
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setUserNickname(nickname);
		int i = userService.addUser(user);
		if (i == 1) {
			map.put("code", 0);
		} else {
			map.put("code", 1);
			map.put("msg", "注册流程异常");
		}
		return map;
	}

	/**
	 * 登录逻辑验证
	 * @param username 前台输入的用户名
	 * @param password 前台输入的密码
	 * @return 返回验证结果和验证信息（如密码错误）
	 */
	@PostMapping("/loginSubmit")
	public Map<String, Object> loginSubmit(String username, String password)
	{
		Map<String, Object> map = new HashMap<>();
		// 获取Subject
		Subject subject = SecurityUtils.getSubject();
		// 封装用户数据
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		// 执行判定逻辑
		int code;
		String msg;
		String url = "";
		try
		{
			subject.login(token);
			// 登录成功
			code = 0;
			msg = "登录成功";
			// 根据角色设置跳转路径，
			User user = (User)subject.getPrincipal();
			// 目前只有一个角色，所以直接确定跳转路径
			url = "/views/assign/index.html";
			// 将用户放入shiro的session中
			subject.getSession().setAttribute("user", user);
		}
		catch (UnknownAccountException e)
		{
			code = -1;
			msg = "用户名不存在";
		}
		catch (IncorrectCredentialsException e)
		{
			code = -1;
			msg = "密码错误";
		}
		map.put("code", code);
		map.put("msg", msg);
		map.put("url", url);

		return map;
	}
}
