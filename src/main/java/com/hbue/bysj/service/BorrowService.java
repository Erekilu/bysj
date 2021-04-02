package com.hbue.bysj.service;

import com.hbue.bysj.domain.Borrow;
import com.hbue.bysj.domain.Trad;
import com.hbue.bysj.domain.User;
import com.hbue.bysj.vo.RecordVo;
import com.hbue.bysj.vo.TradVo;

import java.util.List;

/**
 * BorrowService <br>
 *
 * @author lisiyang <br>
 * @date 2021/3/30 19:44 <br>
 */
public interface BorrowService
{
	/**
	 * 修改目标借贷状态
	 * @param borrowId 借贷id
	 */
	void changeBorrowState(int borrowId);

	/**
	 * 添加借贷记录
	 * @param borrow
	 */
	void addBorrow(Borrow borrow);

	/**
	 * 分页查询当前用户下的借贷记录
	 * @param page 页号
	 * @param limit 每页数量
	 * @return
	 */
	RecordVo<Borrow> getBorrowList(int page, int limit);

	/**
	 * 根据借贷id删除一条记录
	 * @param borrowId 借贷id
	 */
	void removeBorrow(int borrowId);

	/**
	 * 查询当前用户今天产生的借贷记录（最多8条）
	 * @return
	 */
	List<Borrow> getTodayBorrow();
}
