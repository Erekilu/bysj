package com.hbue.bysj.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * ApiResult <br>
 *
 * @author lisiyang <br>
 * @date 2021/3/30 14:24 <br>
 */
@Data
public class ApiResult<T> implements Serializable
{
	/**
	 * 状态码
	 */
	private Integer code;
	/**
	 * 错误码
	 */
	private String errorCode;
	/**
	 * 错误描述
	 */
	private String msg;
	/**
	 * 数据
	 */
	private T data;

	public ApiResult(Integer code, String errorDesc, T data) {
		this.code = code;
		this.msg = errorDesc;
		this.data = data;
	}


	public static <T> ApiResult<T> success() {
		return new ApiResult<T>(0, null, null);
	}

	public static <T> ApiResult<T> success(T data) {
		return new ApiResult<T>(0, null, data);
	}

	public static <T> ApiResult<T> error(String errorDesc) {
		return new ApiResult<T>(1, errorDesc, null);
	}

	public static <T> ApiResult<T> error(String errorDesc, T data) {
		return new ApiResult<T>(1, errorDesc, data);
	}
}
