package com.mcf.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorDTO {

	public ErrorDTO() {
		this.errorCode = 1;
	}

	public ErrorDTO(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public ErrorDTO(Integer errorCode, String msg) {
		this.errorCode = errorCode;
		this.error = new ErrorInfoDTO();
		this.error.setMsg(msg);
	}

	public ErrorDTO(Integer errorCode, String msg, String cause) {
		this.errorCode = errorCode;
		this.error = new ErrorInfoDTO();
		this.error.setMsg(msg);
		this.error.setCause(cause);
	}

	/**
	 * 0 成功,非0为错误
	 */
	@JsonProperty("error_code")
	private Integer errorCode = 1;

	/**
	 * msg：错误信息 cause:引起错误的相关信息（可选）
	 */
	@JsonProperty("error")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private ErrorInfoDTO error;

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public ErrorInfoDTO getError() {
		return error;
	}

	public void setError(ErrorInfoDTO error) {
		this.error = error;
	}

}
