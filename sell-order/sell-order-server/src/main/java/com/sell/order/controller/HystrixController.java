package com.sell.order.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

	// 超时时间设置
	// @HystrixCommand(fallbackMethod = "fallback", commandProperties = {
	// @HystrixProperty(name =
	// "execution.isolation.thread.timeoutInMilliseconds", value = "3000") })
	// 服务熔断
	@HystrixCommand(commandKey="getProductList", commandProperties={
			@HystrixProperty(name="circuitBreaker.enabled", value="true"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="10000"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="60")
	})
	@GetMapping("/getProductInfoList")
	public String getProductList() {
		RestTemplate template = new RestTemplate();
		String result = template.postForObject("http://192.168.1.105:8077/product/listForOrder",
				Arrays.asList("157875196366160022"), String.class);
		return result;
	}

	public String fallback() {
		return "太拥挤了，请稍后再试^(00)^";
	}

	public String defaultFallback() {
		return "太拥挤了，请稍后再试^(00)^";
	}

}
