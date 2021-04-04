package com.hbue.bysj.mapper;

import com.hbue.bysj.vo.TradVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: Erekilu
 * @Date: 2021-04-04
 */
@Repository
public interface StatisticalMapper
{
	/**
	 * 获取当前用户的年/月/周内的收入/支出总和/数量和
	 * @param returnType 1-总和 2-数量和
	 * @param range 1-本周 2-本月 3-本年
	 * @param tradFlag 0-支出 1-收入
	 * @param userId 用户id
	 * @return
	 */
	Integer selectTradSum(@Param("returnType") int returnType, @Param("range") int range,
						  @Param("tradFlag") int tradFlag, @Param("userId") int userId);

	/**
	 * 获取当前用户的年/月/周内的收入/支出对象列表
	 * @param range 1-本周 2-本月 3-本年
	 * @param tradFlag 0-支出 1-收入
	 * @param userId 用户id
	 * @return
	 */
	List<TradVo> selectTradList(@Param("range") int range, @Param("tradFlag") int tradFlag,
								@Param("userId") int userId);
}
