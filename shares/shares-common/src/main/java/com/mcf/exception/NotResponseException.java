package com.mcf.exception;

/**
 * Exception 描述：未响应
 *
 * @author Administrator
 */
public class NotResponseException extends RuntimeException implements
		BaseException {
	private static final long serialVersionUID = 1L;

	public NotResponseException(String msg) {
		super(msg);
	}

	public NotResponseException(String msg, Throwable t) {
		super(msg, t);
	}

	public int getErrorCode() {
		return ExceptionConstant.NOT_RESPONSE_CODE;
	}

}
