package com.hbue.bysj;

import com.hbue.bysj.domain.Borrow;
import com.hbue.bysj.domain.Trad;
import com.hbue.bysj.domain.User;
import com.hbue.bysj.mapper.BorrowMapper;
import com.hbue.bysj.mapper.TradMapper;
import com.hbue.bysj.mapper.UserMapper;
import com.hbue.bysj.vo.TradVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Erekilu
 * @Date: 2021-03-24
 */
@SpringBootTest
@SpringJUnitConfig
public class UserTest
{
	@Autowired
	UserMapper userMapper;
	@Autowired
	TradMapper tradMapper;
	@Autowired
	BorrowMapper borrowMapper;

	@Test
	public void findUserByUsername()
	{
		User user = userMapper.findUserByUsername("user1");
		System.out.println(user);
	}

	@Test
	public void insertSelective()
	{
		Trad trad = new Trad();
//		trad.setTradType(1);
		trad.setTradFlag(2);
		trad.setTradAmount(1400);
//		trad.setTradTarget("xxx");
		trad.setTradName("ttt");
//		trad.setTradDescribe("sdfsdf");
		trad.setTradTime(new Date());
		trad.setTradCreateTime(new Date());
		trad.setTradUpdateTime(new Date());
		tradMapper.insertSelective(trad);
	}

	@Test
	public void insertSelective1()
	{
		Borrow borrow = new Borrow();
		borrow.setBorrowerName("lsy");
		borrow.setLenderName("wyx");
		borrow.setBorrowFlag(1);
		borrow.setBorrowState(0);
		borrow.setBorrowDescribe("xxxxxx");
		borrow.setBorrowAmount(1231);
		borrow.setBorrowTime(new Date());
		borrow.setBorrowCreateTime(new Date());
		borrow.setBorrowUpdateTime(new Date());
		borrowMapper.insertSelective(borrow);
	}

	@Test
	public void selectTodayTrad()
	{
		List<TradVo> tradVos = tradMapper.selectTodayTrad(1);
		System.out.println(tradVos.size());
	}
}
