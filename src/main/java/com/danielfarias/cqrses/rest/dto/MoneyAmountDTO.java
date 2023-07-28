package com.danielfarias.cqrses.rest.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MoneyAmountDTO {
	
	private BigDecimal amount;

}
