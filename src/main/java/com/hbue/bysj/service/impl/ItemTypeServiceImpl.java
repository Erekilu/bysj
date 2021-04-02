package com.hbue.bysj.service.impl;

import com.hbue.bysj.domain.ItemType;
import com.hbue.bysj.exception.CommonErrorEnum;
import com.hbue.bysj.mapper.ItemTypeMapper;
import com.hbue.bysj.service.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ItemTypeServiceImpl <br>
 *
 * @author lisiyang <br>
 * @date 2021/3/31 16:32 <br>
 */
@Service
public class ItemTypeServiceImpl implements ItemTypeService
{
	@Autowired
	private ItemTypeMapper itemTypeMapper;

	/**
	 * 获取物品类型列表
	 * @return
	 */
	@Override
	public List<ItemType> getItemType(int category) {
		if (category != 1 && category != 0) {
			throw CommonErrorEnum.PARAMETER_VALIDATION_ERROR.getBusinessException();
		}
		return itemTypeMapper.selectItemType(category);
	}
}
