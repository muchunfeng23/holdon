package com.mcf.exception;

/**
 * Exception 描述：无权限
 *
 * @author Administrator
 */
public class WithoutLoginException extends RuntimeException implements
		BaseException {
	private static final long serialVersionUID = 1L;

	public WithoutLoginException(String msg) {
		super(msg);
	}

	public WithoutLoginException(String msg, Throwable t) {
		super(msg, t);
	}

	public int getErrorCode() {
		return ExceptionConstant.WITHOUT_LOGIN_CODE;
	}

}
