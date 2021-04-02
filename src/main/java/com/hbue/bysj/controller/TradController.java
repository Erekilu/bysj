package com.hbue.bysj.controller;

import com.hbue.bysj.domain.Trad;
import com.hbue.bysj.domain.User;
import com.hbue.bysj.service.TradService;
import com.hbue.bysj.vo.ApiResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TradController <br>
 *
 * @author lisiyang <br>
 * @date 2021/3/30 14:13 <br>
 */
@RestController
public class TradController
{
	@Autowired
	private TradService tradService;

	/**
	 * 获取当前用户今日的交易收支总和
	 * @return data.in：收入和 data.out：支出和
	 */
	@GetMapping("/todayTradAmount")
	public ApiResult todayTradAmount() {
		return ApiResult.success(tradService.todayTradAmount());
	}

	/**
	 * 获取今天的交易记录（当前用户下）
	 *
	 * @return
	 */
	@GetMapping("/todayTrad")
	public ApiResult todayTrad() {
		return ApiResult.success(tradService.getTodayTrad());
	}

	/**
	 * 删除一条交易记录
	 *
	 * @param tradId 记录id
	 * @return
	 */
	@PostMapping("/removeTrad")
	public ApiResult removeTrad(int tradId) {
		tradService.removeTrad(tradId);
		return ApiResult.success();
	}

	/**
	 * 添加一条交易记录
	 *
	 * @param trad
	 */
	@PostMapping("/addTrad")
	public ApiResult addTrad(Trad trad) {
		tradService.addTrad(trad);
		return ApiResult.success();
	}

	/**
	 * 查询当前用户下的所有交易记录
	 *
	 * @param page  页号
	 * @param limit 每页数量
	 * @return
	 */
	@GetMapping("/getTrads")
	public ApiResult getTrads(int page, int limit) {
		return ApiResult.success(tradService.getTradList(page, limit));
	}
}
