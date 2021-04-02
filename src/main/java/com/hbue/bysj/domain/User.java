package com.hbue.bysj.domain;

import lombok.Data;

@Data
public class User {
  private Integer id;
  // 权限类型 admin:assign 管理员 admin:user 普通用户
  private String userType;
  // 用户名
  private String username;
  // 密码
  private String password;
  // 昵称
  private String userNickname;
  // 性别
  private String userSex;
  // 头像地址
  private String userSrc;
  private java.util.Date userCreateTime;
  private java.util.Date userUpdateTime;
}
