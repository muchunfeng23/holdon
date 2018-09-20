package com.mcf.examples.event;

import org.springframework.context.ApplicationListener;

public class DemoListener implements ApplicationListener<DemoEvent>{

	@Override
	public void onApplicationEvent(DemoEvent event) {
		String msg = event.getMsg();
		System.out.println("接收到的消息为：" + msg);
	}

}
