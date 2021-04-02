package com.hbue.bysj.controller;

import com.hbue.bysj.domain.Borrow;
import com.hbue.bysj.domain.User;
import com.hbue.bysj.service.BorrowService;
import com.hbue.bysj.vo.ApiResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BorrowController <br>
 *
 * @author lisiyang <br>
 * @date 2021/3/30 17:53 <br>
 */
@RestController
public class BorrowController
{
	@Autowired
	private BorrowService borrowService;

	/**
	 * 查询当前用户今天产生的借贷记录（最多8条）
	 * @return
	 */
	@GetMapping("/todayBorrow")
	public ApiResult todayBorrow()
	{
		return ApiResult.success(borrowService.getTodayBorrow());
	}

	/**
	 * 修改借贷记录的状态
	 * @return
	 */
	@PostMapping("/changeBorrowState")
	public ApiResult changeBorrowState(int borrowId)
	{
		borrowService.changeBorrowState(borrowId);
		return ApiResult.success();
	}

	/**
	 * 删除一条借贷记录
	 * @param borrowId 借贷id
	 * @return
	 */
	@PostMapping("/removeBorrow")
	public ApiResult removeBorrow(int borrowId)
	{
		borrowService.removeBorrow(borrowId);
		return ApiResult.success();
	}

	/**
	 * 添加一条借贷记录
	 * @param borrow
	 * @return
	 */
	@PostMapping("/addBorrow")
	public ApiResult addBorrow(Borrow borrow)
	{
		borrowService.addBorrow(borrow);
		return ApiResult.success();
	}

	/**
	 * 查询当前用户下的所有借贷记录
	 * @param page 页号
	 * @param limit 每页数量
	 * @return
	 */
	@GetMapping("/getBorrows")
	public ApiResult getBorrows(int page, int limit) {
		return ApiResult.success(borrowService.getBorrowList(page, limit));
	}
}
