package com.alirizakaygusuz.redis_rate_limiter.service;

public interface RedisRateLimiterService {
	
	boolean isAllowed(String keyString , int limit  , int windowSeconds);

}
