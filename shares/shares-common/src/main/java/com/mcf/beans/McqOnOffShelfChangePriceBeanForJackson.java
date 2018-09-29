package com.mcf.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class McqOnOffShelfChangePriceBeanForJackson {
	@JsonProperty("type")
	private Integer type;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("callback_id")
	private String callbackId;
	
	@JsonProperty("u_t")
	private String ut;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCallbackId() {
		return callbackId;
	}

	public void setCallbackId(String callbackId) {
		this.callbackId = callbackId;
	}

	public String getUt() {
		return ut;
	}

	public void setUt(String ut) {
		this.ut = ut;
	}
	
}
