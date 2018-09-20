package com.mcf.util;

import com.mcf.dto.ServiceResult;
import com.mcf.exception.BaseException;
import com.mcf.exception.ExceptionUtil;

/**
 * Created by Administrator on 2016/9/14.
 */
public class ServiceResultUtil {

	public static <T> ServiceResult<T> success(T body) {
		ServiceResult<T> serviceResult = new ServiceResult<>();
		serviceResult.setSuccess(true);
		serviceResult.setBody(body);
		return serviceResult;
	}

	public static <T> ServiceResult<T> failure(Integer errorCode, String message) {
		ServiceResult<T> serviceResult = new ServiceResult<>();
		serviceResult.setSuccess(false);
		serviceResult.setErrorCode(errorCode);
		serviceResult.setMessage(message);
		return serviceResult;
	}

	public static <T> ServiceResult<T> failure(Integer errorCode, String message, T body) {
		ServiceResult<T> serviceResult = new ServiceResult<>();
		serviceResult.setSuccess(false);
		serviceResult.setErrorCode(errorCode);
		serviceResult.setMessage(message);
		serviceResult.setBody(body);
		return serviceResult;
	}

	public static ServiceResult<RuntimeException> failure(Throwable ex) {
		BaseException be = ExceptionUtil.parseException(ex);

		ServiceResult<RuntimeException> sr = new ServiceResult<>();
		sr.setSuccess(false);
		sr.setErrorCode(be.getErrorCode());
		sr.setMessage(be.getMessage());

		return sr;
	}

}
