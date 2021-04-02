package com.hbue.bysj.service;

import com.hbue.bysj.domain.User;

/**
 * @Auther: Erekilu
 * @Date: 2021-03-24
 */
public interface UserService
{
	/**
	 * 添加用户
	 * @param user 新添加的用户对象
	 * @return 影响行数
	 */
	int addUser(User user);

	/**
	 * 通过用户名来查询目标用户
	 * @param username 用户名(账号)
	 * @return 用户对象
	 */
	User findUserByUsername(String username);

	/**
	 * 更新用户数据
	 * @param admin 封装了部分用户数据的用户对象
	 * @return 更新后的完整用户对象
	 */
	User changeUserById(User admin);
}
