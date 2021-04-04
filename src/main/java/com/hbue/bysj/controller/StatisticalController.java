package com.hbue.bysj.controller;

import com.hbue.bysj.domain.Borrow;
import com.hbue.bysj.mapper.BorrowMapper;
import com.hbue.bysj.mapper.TradMapper;
import com.hbue.bysj.service.BorrowService;
import com.hbue.bysj.service.StatisticalService;
import com.hbue.bysj.service.TradService;
import com.hbue.bysj.vo.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * StatisticController <br>
 *
 * @author lisiyang <br>
 * @date 2021/4/2 10:31 <br>
 */
@RestController
public class StatisticalController
{
	@Autowired
	private StatisticalService statisticalService;

	/**
	 * 查询所有的统计数据
	 * @return
	 */
	@GetMapping("/getMessage")
	public ApiResult getMessage() {
		return ApiResult.success(statisticalService.getTradDetail());
	}
}
