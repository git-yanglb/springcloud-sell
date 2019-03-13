package com.sell.gateway.exception;

public class RateLimiterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RateLimiterException() {
		super();
	}

	public RateLimiterException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RateLimiterException(String message, Throwable cause) {
		super(message, cause);
	}

	public RateLimiterException(String message) {
		super(message);
	}

	public RateLimiterException(Throwable cause) {
		super(cause);
	}

}
