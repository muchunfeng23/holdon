package com.mcf.exception;

/**
 * Exception 描述：远程调用商品异常
 *
 * @author Administrator
 */
public class RpcException extends RuntimeException implements BaseException {
	private static final long serialVersionUID = 1L;

	public RpcException(String msg) {
		super(msg);
	}

	public RpcException(String msg, Throwable t) {
		super(msg, t);
	}

	public int getErrorCode() {
		return ExceptionConstant.RPC_CODE;
	}
}
