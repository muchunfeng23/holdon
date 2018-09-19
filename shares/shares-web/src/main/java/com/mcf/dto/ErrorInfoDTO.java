package com.mcf.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorInfoDTO {
	/**
	 * 错误信息
	 */
	@JsonProperty("msg")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String msg;

	/**
	 * 引起错误的相关信息（可选）
	 */
	@JsonProperty("cause")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String cause;
	
	
	

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
}
