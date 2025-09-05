package com.alirizakaygusuz.redis_rate_limiter.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TransferController {
	
	@GetMapping("/balance")
	public Map<String, Object> getBalance(){
		Map<String, Object> response = new HashMap<>();
		response.put("balance", 1500);
		response.put("currency", "USD");
		
		return response;
	}

}
