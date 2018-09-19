package com.mcf.shares.web;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
	//Random单独作为一个bean,实际是没有必要的
	@Bean
	public Random aRandom() {
		return new Random();
	}

}
