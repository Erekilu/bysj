package com.hbue.bysj.controller;

import com.hbue.bysj.mapper.BorrowMapper;
import com.hbue.bysj.mapper.TradMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * StatisticController <br>
 *
 * @author lisiyang <br>
 * @date 2021/4/2 10:31 <br>
 */
@RestController
public class StatisticController
{
	@Autowired
	private TradMapper tradMapper;
	@Autowired
	private BorrowMapper borrowMapper;
}
