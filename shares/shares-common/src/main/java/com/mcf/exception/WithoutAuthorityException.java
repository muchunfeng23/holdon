package com.mcf.exception;

/**
 * Exception 描述：无权限
 *
 * @author Administrator
 */
public class WithoutAuthorityException extends RuntimeException implements
		BaseException {
	private static final long serialVersionUID = 1L;

	public WithoutAuthorityException(String msg) {
		super(msg);
	}

	public WithoutAuthorityException(String msg, Throwable t) {
		super(msg, t);
	}

	public int getErrorCode() {
		return ExceptionConstant.WITHOUT_AUTHORITY_CODE;
	}

}
