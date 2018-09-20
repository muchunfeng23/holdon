package com.mcf.exception;

/**
 * Exception 描述：用户当前城市变化
 *
 * @author Administrator
 */
public class CurrentCityChangedException extends RuntimeException implements
		BaseException {
	private static final long serialVersionUID = 1L;

	public CurrentCityChangedException(String msg) {
		super(msg);
	}

	public CurrentCityChangedException(String msg, Throwable t) {
		super(msg, t);
	}

	public int getErrorCode() {
		return ExceptionConstant.CITY_CHANGED_CODE;
	}

}
