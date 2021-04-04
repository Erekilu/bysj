package com.hbue.bysj.service.impl;

import com.hbue.bysj.domain.User;
import com.hbue.bysj.mapper.StatisticalMapper;
import com.hbue.bysj.service.StatisticalService;
import com.hbue.bysj.util.SysUtil;
import com.hbue.bysj.vo.TradVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther: Erekilu
 * @Date: 2021-04-04
 */
@Service
public class StatisticalServiceImpl implements StatisticalService
{
	@Autowired
	private StatisticalMapper statisticalMapper;

	@Override
	public Map<String, Object> getTradDetail() {
		Map<String, Object> map = new HashMap<>();
		User user = SysUtil.getUser();
		// 获取本周的支出金额和
		map.put("weekTradOutSum", statisticalMapper.selectTradSum(1, 1, 0, user.getId()));
		// 获取本月的支出金额和
		map.put("monthTradOutSum", statisticalMapper.selectTradSum(1, 2, 0, user.getId()));
		// 获取本年的支出金额和
		map.put("yearTradOutSum", statisticalMapper.selectTradSum(1, 3, 0, user.getId()));

		// 获取本周的支出数量和
		map.put("weekTradOutCount", statisticalMapper.selectTradSum(2, 1, 0, user.getId()));
		// 获取本月的支出数量和
		map.put("monthTradOutCount", statisticalMapper.selectTradSum(2, 2, 0, user.getId()));
		// 获取本年的支出数量和
		map.put("yearTradOutCount", statisticalMapper.selectTradSum(2, 3, 0, user.getId()));

		// 获取本周的收入金额和
		map.put("weekTradInSum", statisticalMapper.selectTradSum(1, 1, 1, user.getId()));
		// 获取本月的收入金额和
		map.put("monthTradInSum", statisticalMapper.selectTradSum(1, 2, 1, user.getId()));
		// 获取本年的收入金额和
		map.put("yearTradInSum", statisticalMapper.selectTradSum(1, 3, 1, user.getId()));

		// 获取本周的收入数量和
		map.put("weekTradInCount", statisticalMapper.selectTradSum(2, 1, 1, user.getId()));
		// 获取本月的收入数量和
		map.put("monthTradInCount", statisticalMapper.selectTradSum(2, 2, 1, user.getId()));
		// 获取本年的收入数量和
		map.put("yearTradInCount", statisticalMapper.selectTradSum(2, 3, 1, user.getId()));

		// 获取本周的支出列表
		List<TradVo> weekTradOutList = statisticalMapper.selectTradList(1, 0, user.getId());
		// 获取本月的支出列表
		List<TradVo> monthTradOutList = statisticalMapper.selectTradList(2, 0, user.getId());
		// 获取本年的支出列表
		List<TradVo> yearTradOutList = statisticalMapper.selectTradList(3, 0, user.getId());

		// 获取本周的收入列表
		List<TradVo> weekTradInList = statisticalMapper.selectTradList(1, 1, user.getId());
		// 获取本月的收入列表
		List<TradVo> monthTradInList = statisticalMapper.selectTradList(2, 1, user.getId());
		// 获取本年的收入列表
		List<TradVo> yearTradInList = statisticalMapper.selectTradList(3, 1, user.getId());

		// 获取支出类型的各种数量
		map.put("weekTradOutMap", getTradTypeMap(weekTradOutList));
		map.put("monthTradOutMap", getTradTypeMap(monthTradOutList));
		map.put("yearTradOutMap", getTradTypeMap(yearTradOutList));
		// 获取收入类型的各种数量
		map.put("weekTradInMap", getTradTypeMap(weekTradInList));
		map.put("monthTradInMap", getTradTypeMap(monthTradInList));
		map.put("yearTradInMap", getTradTypeMap(yearTradInList));

		// 获取不同时段的收支金额和
		map.put("weekTradOutData", getWeekTradData(weekTradOutList));
		map.put("monthTradOutData", getMonthTradData(monthTradOutList));
		map.put("yearTradOutData", getYearTradData(yearTradOutList));

		map.put("weekTradInData", getWeekTradData(weekTradInList));
		map.put("monthTradInData", getMonthTradData(monthTradInList));
		map.put("yearTradInData", getYearTradData(yearTradInList));
		return map;
	}

	/**
	 * 获取本年内不同时段的收支金额和
	 * @param yearTradList
	 * @return
	 */
	private Map<String, Object> getYearTradData(List<TradVo> yearTradList) {
		Map<String, Object> tradTypeMap = new HashMap<>();
		String [] xList = new String[] {"1月", "2月", "3月", "4月", "5月", "6月", "7月",
										"8月", "9月", "10月", "11月", "12月", };
		double [] yList = new double[12];

		// 按时间分类
		Map<Integer, List<TradVo>> map = yearTradList.stream().collect(Collectors.groupingBy((vo) -> vo.getTradTime().getMonth()));
		map.forEach((key, value) -> {
			for (int i = 0 ; i < yList.length ; i++) {
				double count = 0;
				if (key == i) {
					for (TradVo tradVo : value) {
						count += tradVo.getTradAmount() * 0.001;
					}
					yList[i] = ((int)(count * 100)) * 0.01;
				}
			}
		});
		tradTypeMap.put("xList", xList);
		tradTypeMap.put("yList", yList);
		return tradTypeMap;
	}

	/**
	 * 获取本月内不同时段的收支金额和
	 * @param monthTradList
	 * @return
	 */
	private Map<String, Object> getMonthTradData(List<TradVo> monthTradList) {
		Map<String, Object> tradTypeMap = new HashMap<>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int maxDayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		String [] xList = new String[] {"1号", "5号", "10号", "15号", "20号", "25号", maxDayOfMonth + "号"};
		double [] yList = new double[7];

		// 按时间分类
		Map<Date, List<TradVo>> map = monthTradList.stream().collect(Collectors.groupingBy(TradVo::getTradTime));
		Calendar calendar = Calendar.getInstance();
		map.forEach((key, value) -> {
			calendar.setTime(key);
			int dayMonth = calendar.get(Calendar.DAY_OF_MONTH);
			double count = 0;
			for (TradVo tradVo : value) {
				count += tradVo.getTradAmount() * 0.001;
			}
			count = ((int)(count * 100)) * 0.01;
			if (dayMonth == 1) {
				yList[0] = count;
			} else if (dayMonth > 1 && dayMonth <= 5) {
				yList[1] = count;
			} else if (dayMonth > 5 && dayMonth <= 10) {
				yList[2] = count;
			} else if (dayMonth > 10 && dayMonth <= 15) {
				yList[3] = count;
			} else if (dayMonth > 15 && dayMonth <= 20) {
				yList[4] = count;
			} else if (dayMonth > 20 && dayMonth <= 25) {
				yList[5] = count;
			} else if (dayMonth > 25 && dayMonth <= maxDayOfMonth) {
				yList[6] = count;
			}
		});
		tradTypeMap.put("xList", xList);
		tradTypeMap.put("yList", yList);
		return tradTypeMap;
	}

	/**
	 * 获取本周内不同时段的收支金额和
	 * @param weekTradOutList
	 * @return
	 */
	private Map<String, Object> getWeekTradData(List<TradVo> weekTradOutList) {
		Map<String, Object> tradTypeMap = new HashMap<>();
		String [] xList = new String[] {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
		double [] yList = new double[7];

		// 按时间分类
		Map<Date, List<TradVo>> map = weekTradOutList.stream().collect(Collectors.groupingBy(TradVo::getTradTime));
		Calendar calendar = Calendar.getInstance();
		map.forEach((key, value) -> {
			calendar.setTime(key);
			int dayWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
			if (dayWeek == 0) {
				dayWeek = 7;
			}
			for (int i = 0 ; i < yList.length ; i++) {
				double count = 0;
				if (dayWeek == i + 1) {
					for (TradVo tradVo : value) {
						count += tradVo.getTradAmount() * 0.001;
					}
					yList[i] = ((int)(count * 100)) * 0.01;
				}
			}
		});
		tradTypeMap.put("xList", xList);
		tradTypeMap.put("yList", yList);
		return tradTypeMap;
	}

	/**
	 * 统计不同交易类型的数量
	 * @param tradVoList 交易对象列表
	 * @return
	 */
	private Map<String, Double> getTradTypeMap(List<TradVo> tradVoList) {
		Map<String, Double> tradTypeMap = new HashMap<>();
		for (TradVo tradVo : tradVoList) {
			String name = tradVo.getItemType().getItemTypeName();
			tradTypeMap.put(name, tradTypeMap.getOrDefault(name, 0.0) + tradVo.getTradAmount() * 0.001);
		}
		return tradTypeMap;
	}
}
