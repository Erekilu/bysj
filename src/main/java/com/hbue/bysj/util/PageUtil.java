package com.hbue.bysj.util;

/**
 * PageUtil <br>
 *
 * @author lisiyang <br>
 * @date 2021/3/31 14:33 <br>
 */
public class PageUtil
{
	/**
	 * 将pageIndex转化成rowIndex
	 * @param pageIndex 页号
	 * @param pageSize 每一页的数据量
	 * @return rowIndex
	 */
	public static int calculateRowIndex(int pageIndex, int pageSize)
	{
		return pageIndex > 0 ? (pageIndex - 1) * pageSize : 0;
	}

}
