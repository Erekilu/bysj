package com.hbue.bysj.domain;

import lombok.Data;

@Data
public class ItemType {
  private Integer id;
  // 该物品类型所属类型（目前无多级分配，该项为0）
  private Integer itemTypeBelong;
  // 该物品类型属于收入还是支出（0-收入 1-支出）
  private Integer itemTypeCategory;
  // 物品类型名
  private String itemTypeName;
  private java.util.Date itemTypeCreateTime;
  private java.util.Date itemTypeUpdateTime;
}
