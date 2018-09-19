package com.mcf.shares.web.scheduled;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("com.mcf.shares.web.scheduled")
@EnableScheduling
public class TaskScheduledConfig {

}
