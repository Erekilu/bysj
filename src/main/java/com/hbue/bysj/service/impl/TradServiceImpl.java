package com.hbue.bysj.service.impl;

import com.hbue.bysj.constant.user;
import com.hbue.bysj.domain.Borrow;
import com.hbue.bysj.domain.Trad;
import com.hbue.bysj.domain.User;
import com.hbue.bysj.exception.CommonErrorEnum;
import com.hbue.bysj.mapper.TradMapper;
import com.hbue.bysj.service.TradService;
import com.hbue.bysj.util.PageUtil;
import com.hbue.bysj.util.SysUtil;
import com.hbue.bysj.vo.RecordVo;
import com.hbue.bysj.vo.TradVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TradServiceImpl <br>
 *
 * @author lisiyang <br>
 * @date 2021/3/30 16:59 <br>
 */
@Service
public class TradServiceImpl implements TradService
{
	@Autowired
	private TradMapper tradMapper;

	/**
	 * 获取当前用户今日的交易收支总和
	 * @return in：收入和 out：支出和
	 */
	@Override
	public Map<String, Object> todayTradAmount() {
		Map<String, Object> map = new HashMap<>();
		User user = SysUtil.getUser();
		map.put("in", tradMapper.selectTodayTradIn(user.getId()));
		map.put("out", tradMapper.selectTodayTradOut(user.getId()));
		return map;
	}

	/**
	 * 添加一条交易记录
	 * @param trad
	 */
	@Override
	public void addTrad(Trad trad) {
		User user = SysUtil.getUser();
		// 绑定用户
		trad.setUserId(user.getId());
		trad.setTradCreateTime(new Date());
		trad.setTradUpdateTime(new Date());
		if (tradMapper.insertSelective(trad) != 1) {
			throw CommonErrorEnum.ADD_FAIL.getBusinessException();
		}
	}

	/**
	 * 分页查询当前用户下的交易记录
	 * @param page 页号
	 * @param limit 每页数量
	 * @return
	 */
	@Override
	public RecordVo<TradVo> getTradList(int page, int limit) {
		User user = SysUtil.getUser();
		// 根据页号计算出行号
		int rowIndex = PageUtil.calculateRowIndex(page, limit);
		RecordVo<TradVo> recordVo = new RecordVo<>();
		recordVo.setRecordList(tradMapper.selectTradList(user.getId(), rowIndex, limit));
		recordVo.setCount(tradMapper.selectTradListCount(user.getId()));
		return recordVo;
	}

	/**
	 * 根据tradId删除一条记录
	 * @param tradId
	 */
	@Override
	public void removeTrad(int tradId) {
		if (tradMapper.deleteById(tradId) != 1) {
			throw CommonErrorEnum.DELETE_FAIL.getBusinessException();
		}
	}


	/**
	 * 查询当前用户今天产生的交易记录（最多8条）
	 * @return
	 */
	@Override
	public List<TradVo> getTodayTrad() {
		return tradMapper.selectTodayTrad(SysUtil.getUser().getId());
	}

}
