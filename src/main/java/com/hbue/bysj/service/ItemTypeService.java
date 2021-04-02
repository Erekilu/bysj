package com.hbue.bysj.service;

import com.hbue.bysj.domain.ItemType;

import java.util.List;

/**
 * ItemTypeService <br>
 *
 * @author lisiyang <br>
 * @date 2021/3/31 16:15 <br>
 */
public interface ItemTypeService
{
	/**
	 * 获取物品类型列表
	 * @return
	 */
	List<ItemType> getItemType(int category);
}
