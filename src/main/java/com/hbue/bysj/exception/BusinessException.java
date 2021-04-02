package com.hbue.bysj.exception;

/**
 * BusinessException <br>
 *
 * @author lisiyang <br>
 * @date 2021/3/30 14:40 <br>
 */
class BusinessException extends RuntimeException
{
	private CommonErrorEnum commonErrorEnum;

	BusinessException(CommonErrorEnum commonErrorEnum) {
		super();
		this.commonErrorEnum = commonErrorEnum;
	}

	String getErrorCode() {
	return commonErrorEnum.getErrorCode();
}

	String getErrorDesc() {
		return commonErrorEnum.getErrorDesc();
	}
}
