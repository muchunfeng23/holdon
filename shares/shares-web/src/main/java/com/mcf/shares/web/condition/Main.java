package com.mcf.shares.web.condition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String args[]) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConditionConfig.class);
		ListService listService = context.getBean(ListService.class);
		System.out.println(context.getEnvironment().getProperty("os.name") + "下的列表命令为：" + listService.showListCmd());
		context.close();
	}
}
