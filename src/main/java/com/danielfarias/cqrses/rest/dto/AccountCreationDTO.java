package com.danielfarias.cqrses.rest.dto;

import java.math.BigDecimal;

import lombok.Value;

@Value
public class AccountCreationDTO {
	
	private final BigDecimal initialBalance;
	
    private final String owner;

}
