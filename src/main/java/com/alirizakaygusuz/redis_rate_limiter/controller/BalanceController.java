package com.alirizakaygusuz.redis_rate_limiter.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alirizakaygusuz.redis_rate_limiter.dto.TransferRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class BalanceController {
	
	@PostMapping("/transfer")
	public Map<String, Object> transfer(@Valid @RequestBody TransferRequest transferRequest){
		Map<String ,Object> response = new HashMap<>();
		
		response.put("transactionId", UUID.randomUUID().toString());
		response.put("to", transferRequest.getTo());
		response.put("amount", transferRequest.getAmount());
		response.put("status", "SUCCESS");
		
		return response;
	}

}
