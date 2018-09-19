package com.mcf.shares.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ImportResource({ 
//	"classpath:config/*-dubbo.xml",
//	"classpath:config/*-publish.xml", 
//	"classpath:config/*-ref.xml",
//	"classpath:config/sprucetec-cronjob.xml",
//	"classpath:config/spring-mcq.xml" 
})

@SpringBootApplication
@ComponentScan(basePackages={"com.mcf","com.mcf.shares"})
public class SharesAppMain {
	public static void main(String args[]) {
		SpringApplication.run(SharesAppMain.class, args);
	}
}
