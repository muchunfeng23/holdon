package com.mcf.shares.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.ImportResource;

//@ImportResource({ "classpath:config/*-dubbo.xml", "classpath:config/*-ref.xml" })
@SpringBootApplication
@ComponentScan(basePackages = { "com.mcf", "com.mcf.shares" })
public class SharesWebMain {
	public static void main(String[] args) {
		SpringApplication.run(SharesWebMain.class, args);
	}
}
