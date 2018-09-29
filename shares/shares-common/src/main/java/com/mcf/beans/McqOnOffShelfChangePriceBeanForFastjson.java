package com.mcf.beans;

import com.alibaba.fastjson.annotation.JSONField;

public class McqOnOffShelfChangePriceBeanForFastjson {
	@JSONField(name = "type")
	private Integer type;
	
	@JSONField(name = "id")
	private String id;
	
	@JSONField(name = "callback_id")
	private String callbackId;
	
	@JSONField(name = "u_t")
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
