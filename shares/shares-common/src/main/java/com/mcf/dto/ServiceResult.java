package com.mcf.dto;

import java.io.Serializable;

public class ServiceResult<T> implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2538918417960606917L;
	
	private Boolean success = Boolean.valueOf(true);
	private int errorCode;
	private String message;
	private T body;

	public ServiceResult() {
	}

	public ServiceResult(T body) {
		this.body = body;
	}

	public ServiceResult(int errorCode, String message) {
		this.success = Boolean.valueOf(false);
		this.errorCode = errorCode;
		this.message = message;
	}

	public Boolean getSuccess() {
		return this.success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public int getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getBody() {
		return this.body;
	}

	public void setBody(T body) {
		this.body = body;
	}
}
