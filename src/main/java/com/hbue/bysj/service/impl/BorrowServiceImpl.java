package com.hbue.bysj.service.impl;

import com.hbue.bysj.constant.user;
import com.hbue.bysj.domain.Borrow;
import com.hbue.bysj.domain.User;
import com.hbue.bysj.exception.CommonErrorEnum;
import com.hbue.bysj.mapper.BorrowMapper;
import com.hbue.bysj.service.BorrowService;
import com.hbue.bysj.util.PageUtil;
import com.hbue.bysj.util.SysUtil;
import com.hbue.bysj.vo.RecordVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * BorrowServiceImpl <br>
 *
 * @author lisiyang <br>
 * @date 2021/3/30 19:45 <br>
 */
@Service
public class BorrowServiceImpl implements BorrowService
{
	@Autowired
	private BorrowMapper borrowMapper;

	/**
	 * 修改目标借贷状态
	 * @param borrowId 借贷id
	 */
	@Override
	public void changeBorrowState(int borrowId) {
		if (borrowMapper.updateStateById(borrowId) != 1) {
			throw CommonErrorEnum.UPDATE_FAIL.getBusinessException();
		}
	}

	/**
	 * 添加借贷记录
	 * @param borrow
	 */
	@Override
	public void addBorrow(Borrow borrow) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User)subject.getPrincipal();
		// 绑定用户
		borrow.setUserId(user.getId());
		// 设置为未还清
		borrow.setBorrowState(1);
		borrow.setBorrowCreateTime(new Date());
		borrow.setBorrowUpdateTime(new Date());
		if (borrowMapper.insertSelective(borrow) != 1) {
			throw CommonErrorEnum.ADD_FAIL.getBusinessException();
		}
	}

	/**
	 * 分页查询当前用户下的借贷记录
	 * @param page 页号
	 * @param limit 每页数量
	 * @return
	 */
	@Override
	public RecordVo<Borrow> getBorrowList(int page, int limit) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		// 根据页号计算出行号
		int rowIndex = PageUtil.calculateRowIndex(page, limit);
		RecordVo<Borrow> recordVo = new RecordVo<>();
		recordVo.setRecordList(borrowMapper.selectBorrowList(user.getId(), rowIndex, limit));
		recordVo.setCount(borrowMapper.selectBorrowListCount(user.getId()));
		return recordVo;
	}

	/**
	 * 根据借贷id删除一条记录
	 * @param borrowId 借贷id
	 */
	@Override
	public void removeBorrow(int borrowId) {
		if (borrowMapper.deleteById(borrowId) != 1) {
			throw CommonErrorEnum.DELETE_FAIL.getBusinessException();
		}
	}

	/**
	 * 查询当前用户今天产生的借贷记录（最多8条）
	 * @return
	 */
	@Override
	public List<Borrow> getTodayBorrow() {
		return borrowMapper.selectTodayBorrow(SysUtil.getUser().getId());
	}
}
