package com.mcf.exception;

public class SystemException extends RuntimeException implements BaseException {
	private static final long serialVersionUID = 1L;

	public SystemException(String msg) {
		super(msg);
	}

	public SystemException(String msg, Throwable t) {
		super(msg, t);
	}

	public int getErrorCode() {
		return ExceptionConstant.SYSTEM_ERROR_CODE;
	}
}
