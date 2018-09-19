package com.mcf.shares.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class ExceptionHandle {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandle.class);

//	@ExceptionHandler(value = Exception.class)
//	@ResponseBody
//	public Result<Object> errorHandler(HttpServletRequest req, Exception e) throws Exception {
//		
//		LOGGER.error(e.getMessage(), e);
//		if (e instanceof ParamException) {
//			return Result.buildInvalidParamResult((ParamException) e);
//		}
//		if (e instanceof BusinessException) {
//			return Result.buildBusinessErrorResult((BusinessException) e);
//		}
//
//		return Result.buildExceptionResult();
//	}
}
