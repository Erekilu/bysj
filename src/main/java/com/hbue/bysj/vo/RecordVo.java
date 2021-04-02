package com.hbue.bysj.vo;

import lombok.Data;

import java.util.List;

/**
 * RecordVo <br>
 *
 * @author lisiyang <br>
 * @date 2021/3/31 15:41 <br>
 */
@Data
public class RecordVo<T>
{
	/**
	 * 记录数量
	 */
	private int count;

	/**
	 * 单条记录
	 */
	private T record;

	/**
	 * 多条记录
	 */
	private List<T> recordList;
}
