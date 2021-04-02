package com.hbue.bysj.mapper;

import com.hbue.bysj.domain.Borrow;
import com.hbue.bysj.domain.Trad;
import com.hbue.bysj.vo.TradVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BorrowMapper <br>
 *
 * @author lisiyang <br>
 * @date 2021/3/30 17:54 <br>
 */
@Repository
public interface BorrowMapper
{
	/**
	 * 修改借贷状态
	 * @param borrowId
	 * @return
	 */
	Integer updateStateById(int borrowId);

	/**
	 * 插入一条借贷记录
	 * @param borrow 借贷记录
	 * @return
	 */
	Integer insertSelective(Borrow borrow);

	/**
	 * 分页查询当前用户下的借贷记录
	 * @param userId 用户id
	 * @param rowStart 起始行号
	 * @param pageSize 每页数量
	 * @return
	 */
	List<Borrow> selectBorrowList(@Param("userId") int userId,
							  @Param("rowStart") int rowStart, @Param("pageSize") int pageSize);

	/**
	 * 查询当前用户下的借贷记录数量
	 * @param userId 用户id
	 * @return
	 */
	Integer selectBorrowListCount(int userId);

	/**
	 * 根据借贷id删除一条记录
	 * @param borrowId 借贷id
	 */
	Integer deleteById(int borrowId);

	/**
	 * 查询当前用户今天产生的借贷记录（最多8条）
	 * @return
	 */
	List<Borrow> selectTodayBorrow(int userId);
}
