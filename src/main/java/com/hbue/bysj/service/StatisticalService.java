package com.hbue.bysj.service;

import java.util.Map;

/**
 * @Auther: Erekilu
 * @Date: 2021-04-04
 */
public interface StatisticalService
{
	/**
	 * 获取当前用户的交易金额之和/数量和
	 * @return
	 */
	Map<String, Object> getTradDetail();
}
