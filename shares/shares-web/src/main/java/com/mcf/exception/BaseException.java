package com.mcf.exception;

/**
 * Created by Administrator on 2016/9/23.
 */
public interface BaseException {
	/**
	 * 获得异常码
	 * 
	 * @return
	 */
	int getErrorCode();

	/**
	 * 获得异常信息
	 * 
	 * @return
	 */
	String getMessage();

}
