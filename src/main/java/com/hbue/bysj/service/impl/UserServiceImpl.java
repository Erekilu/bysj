package com.hbue.bysj.service.impl;

import com.hbue.bysj.domain.User;
import com.hbue.bysj.mapper.UserMapper;
import com.hbue.bysj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Auther: Erekilu
 * @Date: 2021-03-24
 */
@Component
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserMapper userMapper;

	/**
	 * 添加用户
	 * @param user 新添加的用户对象
	 * @return 影响行数
	 */
	@Override
	public int addUser(User user) {
		user.setUserSex("男");
		user.setUserType("admin:assign");
		user.setUserCreateTime(new Date());
		user.setUserUpdateTime(new Date());
		return userMapper.insertUser(user);
	}

	/**
	 * 通过用户名来查询目标用户
	 * @param username 用户名(账号)
	 * @return 用户对象
	 */
	@Override
	public User findUserByUsername(String username) {
		return userMapper.findUserByUsername(username);
	}

	/**
	 * 更新用户数据
	 * @param user 封装了部分用户数据的用户对象
	 * @return 更新后的完整用户对象
	 */
	@Override
	public User changeUserById(User user) {
		user.setUserUpdateTime(new Date());
		userMapper.changeUserById(user);
		return userMapper.findUserById(user.getId());
	}
}
