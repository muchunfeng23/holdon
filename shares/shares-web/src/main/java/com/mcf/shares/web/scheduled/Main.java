package com.mcf.shares.web.scheduled;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String args[]) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskScheduledConfig.class);
	}
}
