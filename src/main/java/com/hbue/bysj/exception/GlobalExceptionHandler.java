package com.hbue.bysj.exception;

import com.hbue.bysj.vo.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获 <br>
 *
 * @author lisiyang <br>
 * @date 2021/3/30 14:50 <br>
 */
@RestControllerAdvice(basePackages = "com.hbue.bysj.*",annotations = Controller.class)
public class GlobalExceptionHandler
{
	/**
	 * 捕获业务异常
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.OK)
	public ApiResult handleBusinessException(Exception ex) {
		BusinessException businessException = (BusinessException)ex;
		return ApiResult.error(businessException.getErrorDesc());
	}

	/**
	 * 捕获其他异常
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	public ApiResult handleException(Exception ex) {
		ex.printStackTrace();
		return ApiResult.error(CommonErrorEnum.UNKNOWN_ERROR.getErrorDesc());
	}
}
