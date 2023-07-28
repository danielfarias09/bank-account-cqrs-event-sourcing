package com.danielfarias.cqrses.event;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class AccountCreatedEvent {
	
	private final UUID id;
	
    private final BigDecimal initialBalance;
    
    private final String owner;

}
