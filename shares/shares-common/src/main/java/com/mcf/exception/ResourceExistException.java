package com.mcf.exception;

/**
 * Exception
 * 描述：资源存在
 *
 * @author Administrator
 */
public class ResourceExistException extends RuntimeException implements BaseException {
	private static final long serialVersionUID = 1L;
	
	public ResourceExistException(String msg) {
		super(msg);
	}
	
	public ResourceExistException(String msg, Throwable t) {
		super(msg, t);
	}
	
	
	public int getErrorCode() {
		return ExceptionConstant.RESOURCE_EXIST_ERROR_CODE;
	}
	
}
