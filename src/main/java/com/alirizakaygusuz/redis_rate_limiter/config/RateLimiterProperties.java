package com.alirizakaygusuz.redis_rate_limiter.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "rate.limiter")
@Getter
@Setter
public class RateLimiterProperties {
	 
	private Rule balance;
	private Rule transfer;
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Rule{
		private int limit;
		private int windowSeconds;
		
	}

}
