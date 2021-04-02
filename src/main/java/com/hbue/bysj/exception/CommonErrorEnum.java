package com.hbue.bysj.exception;

/**
 * CommonErrorEnum <br>
 *
 * @author lisiyang <br>
 * @date 2021/3/30 14:41 <br>
 */
public enum CommonErrorEnum
{	//10000通用错误
	UNKNOWN_ERROR("BYSJ-10001","未知错误"),
	PARAMETER_VALIDATION_ERROR("BYSJ-10002","参数不合法"),
	ADD_FAIL("BYSJ-10004","添加失败"),
	DELETE_FAIL("BYSJ-10005","删除失败"),
	UPDATE_FAIL("BYSJ-10006","更新失败"),
	FIND_FAIL("BYSJ-10007","资源不存在"),
	;

	private String errorCode;
	private String errorDesc;

	CommonErrorEnum(String errorCode, String errorDesc) {
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public BusinessException getBusinessException(){
		return new BusinessException(this);
	}
}
