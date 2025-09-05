package com.alirizakaygusuz.redis_rate_limiter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransferRequest {

	@NotBlank
	private String to;
	
	@Positive
	private double amount;
}
