package com.hbue.bysj.mapper;

import com.hbue.bysj.domain.Trad;
import com.hbue.bysj.vo.TradVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TradMapper <br>
 *
 * @author lisiyang <br>
 * @date 2021/3/30 13:51 <br>
 */
@Repository
public interface TradMapper
{
	/**
	 * 获取当前用户今日的交易收入总和
	 * @param userId
	 * @return
	 */
	Integer selectTodayTradIn(int userId);

	/**
	 * 获取当前用户今日的交易支出总和
	 * @param userId
	 * @return
	 */
	Integer selectTodayTradOut(int userId);

	/**
	 * 插入一条交易记录
	 * @param trad 交易记录
	 * @return
	 */
	Integer insertSelective(Trad trad);

	/**
	 * 分页查询当前用户下的交易记录
	 * @param userId 用户id
	 * @param rowStart 起始行号
	 * @param pageSize 每页数量
	 * @return
	 */
	List<TradVo> selectTradList(@Param("userId") int userId,
							  @Param("rowStart") int rowStart, @Param("pageSize") int pageSize);

	/**
	 * 查询当前用户下的交易记录数量
	 * @param userId 用户id
	 * @return
	 */
	Integer selectTradListCount(int userId);

	/**
	 * 根据tradId删除一条记录
	 * @param tradId
	 * @return
	 */
	Integer deleteById(int tradId);

	/**
	 * 查询当前用户今天产生的交易记录（最多8条）
	 * @return
	 */
	List<TradVo> selectTodayTrad(int userId);
}
