package com.hbue.bysj.mapper;

import com.hbue.bysj.domain.ItemType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ItemTypeMapper <br>
 *
 * @author lisiyang <br>
 * @date 2021/3/31 16:33 <br>
 */
@Repository
public interface ItemTypeMapper
{
	/**
	 * 获取物品类型列表
	 * @return
	 */
	List<ItemType> selectItemType(int category);
}
