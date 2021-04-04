package com.hbue.bysj.service;

import com.hbue.bysj.domain.Trad;
import com.hbue.bysj.vo.RecordVo;
import com.hbue.bysj.vo.TradVo;

import java.util.List;
import java.util.Map;

/**
 * TradService <br>
 *
 * @author lisiyang <br>
 * @date 2021/3/30 16:55 <br>
 */
public interface TradService
{
	/**
	 * 获取当前用户今日的交易收支总和
	 * @return in：收入和 out：支出和
	 */
	Map<String, Object> todayTradAmount();

	/**
	 * 添加一条交易记录
	 * @param trad
	 */
	void addTrad(Trad trad);

	/**
	 * 分页查询当前用户下的交易记录
	 * @param page 页号
	 * @param limit 每页数量
	 * @return
	 */
	RecordVo<TradVo> getTradList(int page, int limit);

	/**
	 * 根据tradId删除一条记录
	 * @param tradId
	 */
	void removeTrad(int tradId);

	/**
	 * 查询当前用户今天产生的交易记录（最多8条）
	 * @return
	 */
	List<TradVo> getTodayTrad();
}
