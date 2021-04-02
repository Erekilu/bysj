package com.hbue.bysj.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Borrow {
  private Integer id;
  // 用户id
  private int userId;
  // 借贷简介
  private String borrowName;
  // 支出人姓名
  private String borrowerName;
  // 收款人姓名
  private String lenderName;
  // 状态（0-已还清 1-未还清）
  private Integer borrowState;
  // 标记（0-贷 1-借）
  private Integer borrowFlag;
  // 详情
  private String borrowDescribe;
  // 借贷金额（以分为单位）
  private Integer borrowAmount;
  // 借贷时间
  @DateTimeFormat(pattern="yyyy-MM-dd")
  @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
  private java.util.Date borrowTime;
  private java.util.Date borrowCreateTime;
  private java.util.Date borrowUpdateTime;
}
