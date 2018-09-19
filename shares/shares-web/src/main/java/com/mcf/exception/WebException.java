package com.mcf.exception;

/**
 * Exception 描述：网页错误
 *
 * @author Administrator
 */
public class WebException extends RuntimeException implements BaseException {
	private static final long serialVersionUID = 1L;

	public WebException(String msg) {
		super(msg);
	}

	public WebException(String msg, Throwable t) {
		super(msg, t);
	}

	public int getErrorCode() {
		return ExceptionConstant.WEB_CODE;
	}

}
