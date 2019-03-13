package com.sell.gateway.filter;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import com.sell.gateway.exception.RateLimiterException;

@Component
public class RateLimiterFilter extends ZuulFilter {

	private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		if (!RATE_LIMITER.tryAcquire()) {
			throw new RateLimiterException();
		}
		return null;
	}

	@Override
	public String filterType() {
		return PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return SERVLET_DETECTION_FILTER_ORDER - 1;
	}

}
