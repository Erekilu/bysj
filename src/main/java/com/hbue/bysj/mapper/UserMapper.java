package com.hbue.bysj.mapper;

import com.hbue.bysj.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Auther: Erekilu
 * @Date: 2021-03-24
 */
@Repository
public interface UserMapper
{
	/**
	 * 通过用户名来查询目标用户
	 * @param username 用户名(账号)
	 * @return 用户对象
	 */
	User findUserByUsername(String username);

	/**
	 * 根据用户id查询用户
	 * @param id 用户id
	 * @return 用户对象
	 */
	User findUserById(int id);

	/**
	 * 添加用户
	 * @param user 新添加的用户对象
	 * @return 影响行数
	 */
	Integer insertUser(User user);

	/**
	 * 改变目标用户的部分属性
	 * @param user 目标用户，含有id和其他需要修改的属性
	 * @return 该操作影响的数据库条数
	 */
	Integer changeUserById(User user);
}
