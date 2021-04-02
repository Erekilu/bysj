package com.hbue.bysj.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hbue.bysj.domain.ItemType;
import com.hbue.bysj.domain.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * TradVo <br>
 *
 * @author lisiyang <br>
 * @date 2021/4/1 14:25 <br>
 */
@Data
public class TradVo
{
	private Integer id;
	// 用户id
	private User user;
	// 交易类型。外键，与物品类型连接
	private ItemType itemType;
	// 标记（0-支出 1-收入）
	private Integer tradFlag;
	// 交易金额（以分为单位）
	private Integer tradAmount;
	// 交易目标
	private String tradTarget;
	// 交易简介
	private String tradName;
	// 交易详情
	private String tradDescribe;
	// 交易时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private java.util.Date tradTime;
	private java.util.Date tradCreateTime;
	private java.util.Date tradUpdateTime;
}
