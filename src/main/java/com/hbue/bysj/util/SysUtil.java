package com.hbue.bysj.util;

import com.hbue.bysj.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * SysUtil <br>
 *
 * @author lisiyang <br>
 * @date 2021/4/1 14:06 <br>
 */
public class SysUtil
{
	public static User getUser() {
		Subject subject = SecurityUtils.getSubject();
		return (User) subject.getPrincipal();
	}
}
