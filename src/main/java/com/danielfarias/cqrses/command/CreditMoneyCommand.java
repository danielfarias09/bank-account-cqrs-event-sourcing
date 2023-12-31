package com.danielfarias.cqrses.command;

import java.math.BigDecimal;
import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditMoneyCommand {
	
	@TargetAggregateIdentifier
	private UUID accountId;
	
	private BigDecimal creditAmount;

}
