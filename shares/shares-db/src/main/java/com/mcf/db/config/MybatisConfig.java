package com.mcf.db.config;

import java.util.Properties;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.github.pagehelper.PageHelper;

@Configuration
public class MybatisConfig {
	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("pageSizeZero", "true");
		properties.setProperty("reasonable", "false");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("returnPageInfo", "check");
		properties.setProperty("params", "count=countSql");
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		pageHelper.setProperties(properties);
		return pageHelper;
	}

//	@Bean
//	public ServletRegistrationBean duridServletRegistrationBean() {
//		StatViewServlet servlet = new StatViewServlet();
//		ServletRegistrationBean registration = new ServletRegistrationBean();
//		registration.setServlet(servlet);
//		registration.setEnabled(true);
//		registration.addUrlMappings(new String[]{"/druid/*"});
//		return registration;
//	}
}
