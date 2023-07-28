package com.danielfarias.cqrses.event;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Value;

@Value
public class MoneyCreditEvent {
	
	private final UUID id;
	
	private final BigDecimal creditAmount;

}
