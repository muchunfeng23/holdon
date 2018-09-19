package com.mcf.shares.web.event;

import org.springframework.context.ApplicationEvent;

public class DemoEvent extends ApplicationEvent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 112002610389028532L;
	
	private String msg;
	
	public DemoEvent(Object source,String msg) {
		super(source);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
