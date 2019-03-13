package com.sell.config.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sell.config.filter.RefreshBodyFilter;

@Configuration
public class ServletConfig {

	@Bean
	public FilterRegistrationBean<RefreshBodyFilter> refreshBodyFilter() {
		FilterRegistrationBean<RefreshBodyFilter> registrationBean = new FilterRegistrationBean<RefreshBodyFilter>();
		registrationBean.setFilter(new RefreshBodyFilter());
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}

}
