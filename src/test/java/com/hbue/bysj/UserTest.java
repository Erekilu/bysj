package com.hbue.bysj;

import com.hbue.bysj.domain.Borrow;
import com.hbue.bysj.domain.Trad;
import com.hbue.bysj.domain.User;
import com.hbue.bysj.mapper.BorrowMapper;
import com.hbue.bysj.mapper.StatisticalMapper;
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
	StatisticalMapper statisticalMapper;

//	@Test
//	void selectTradSum()
//	{
//		int i = statisticalMapper.selectTradSum(2, 1, 0, 1);
//		System.out.println(i);
//	}
//
//	@Test
//	void selectTradList()
//	{
//		List<TradVo> tradVos = statisticalMapper.selectTradList(2, 1, 1);
//		System.out.println(tradVos.size());
//	}
}
