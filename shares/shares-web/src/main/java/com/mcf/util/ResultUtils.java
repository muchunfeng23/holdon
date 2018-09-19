package com.mcf.util;

import com.mcf.dto.ErrorDTO;
import com.mcf.dto.ResultDTO;

/**
 * 通用返回结果类
 * 
 * @author
 *
 */
public class ResultUtils<T> {

	/**
	 * 正确返回信息
	 * 
	 * @param data
	 * @return
	 */
	public static <T> ResultDTO<T> success(T data, Integer totalRecords,
			Integer recordsPerpage, Integer currentPage) {
		return new ResultDTO<T>(data, totalRecords, recordsPerpage, currentPage);
	}

	/**
	 * 正确返回信息
	 * 
	 * @param data
	 * @return
	 */
	public static <T> ResultDTO<T> success(T data, Integer totalRecords,
			Integer totalPages, Integer recordsPerpage, Integer currentPage) {
		return new ResultDTO<T>(data, totalRecords, totalPages, recordsPerpage,
				currentPage);
	}

	/**
	 * 正确返回信息
	 * 
	 * @param data
	 * @return
	 */
	public static <T> ResultDTO<T> success(T data) {
		return new ResultDTO<T>(data);
	}

	/**
	 * 错误返回信息
	 * 
	 * @param errorCode
	 * @param message
	 * @param cause
	 * @return
	 */
	public static ErrorDTO error(Integer errorCode, String message, String cause) {

		return new ErrorDTO(errorCode, message, cause);
	}

	/**
	 * 错误返回信息
	 * 
	 * @param errorCode
	 * @param msg
	 * @return
	 */
	public static ErrorDTO error(Integer errorCode, String msg) {

		return new ErrorDTO(errorCode, msg);
	}

}