package com.mcf.exception;

/**
 * Exception 描述：
 *
 * @author Administrator
 */
public class BusinessException extends RuntimeException implements
		BaseException {
	private static final long serialVersionUID = 1L;

	public BusinessException(String msg) {
		super(msg);
	}

	public BusinessException(String msg, Throwable t) {
		super(msg, t);
	}

	public int getErrorCode() {
		return ExceptionConstant.BUSINISS_ERROR_CODE;
	}

}
