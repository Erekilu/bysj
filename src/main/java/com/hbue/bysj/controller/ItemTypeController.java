package com.hbue.bysj.controller;

import com.hbue.bysj.service.ItemTypeService;
import com.hbue.bysj.vo.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ItemTypeController <br>
 *
 * @author lisiyang <br>
 * @date 2021/3/31 16:15 <br>
 */
@RestController
public class ItemTypeController
{
	@Autowired
	private ItemTypeService itemTypeService;

	@GetMapping("/item_type")
	public ApiResult getItemType(int category)
	{
		return ApiResult.success(itemTypeService.getItemType(category));
	}
}
