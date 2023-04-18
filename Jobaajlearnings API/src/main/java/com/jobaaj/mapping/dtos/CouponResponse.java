package com.jobaaj.mapping.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CouponResponse {
	
	private int amount;
	private String message;
	private boolean success;

}
