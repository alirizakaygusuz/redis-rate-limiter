package com.alirizakaygusuz.redis_rate_limiter.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alirizakaygusuz.redis_rate_limiter.service.RedisRateLimiterService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor

public class RateLimiterInterceptor implements HandlerInterceptor {

    private final RateLimiterProperties rateLimiterProperties;

	private final RedisRateLimiterService rateLimiterService;
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String username = "Ali";
		
		String endpoint = request.getRequestURI();
		
		
		//Pick rule that is matching from application.yml
		RateLimiterProperties.Rule rule = null;
		
		if(endpoint.contains("balance")) {
			rule = rateLimiterProperties.getBalance();
		}else if(endpoint.contains("transfer")) {
			rule = rateLimiterProperties.getTransfer();
		}
		
		//IF no rule -no rate limit
		if(rule ==null) {
			 return true;
		}
		
		
	    // Redis key format = rate:/api/endpoint:username
		//Ensure different key for every user
		String key= "rate:" +endpoint + ":" + username;
		
		
		boolean allowed = rateLimiterService.isAllowed(key, rule.getLimit(), rule.getWindowSeconds());
		
		
		if(!allowed) {
			response.setStatus(429);
			response.setContentType("application/json");
			response.getWriter().write("Exceeded the limit of request");
			return false;
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	
	
	
}
