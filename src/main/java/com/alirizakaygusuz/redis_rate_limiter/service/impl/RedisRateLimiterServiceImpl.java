package com.alirizakaygusuz.redis_rate_limiter.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alirizakaygusuz.redis_rate_limiter.service.RedisRateLimiterService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RedisRateLimiterServiceImpl implements RedisRateLimiterService {

	private final StringRedisTemplate redisTemplate; 
	
	@Override
	public boolean isAllowed(String key, int limit, int windowSeconds) {
		Long count = redisTemplate.opsForValue().increment(key);
		
		if(count != null && count==1) {
			redisTemplate.expire(key, windowSeconds, TimeUnit.SECONDS);
		}
		
		return count != null && count <= limit;
		
	}

}
