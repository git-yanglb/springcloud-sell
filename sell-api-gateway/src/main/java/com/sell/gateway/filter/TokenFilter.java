package com.sell.gateway.filter;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

@Component
public class TokenFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
//		RequestContext requestContext = RequestContext.getCurrentContext();
//		HttpServletRequest request = requestContext.getRequest();
//		String token = request.getHeader("token");
//		if (StringUtils.isBlank(token)) {
//			requestContext.setSendZuulResponse(false);
//			requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
//		}
		return null;
	}

	@Override
	public String filterType() {
		return PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return PRE_DECORATION_FILTER_ORDER - 1;
	}

}
