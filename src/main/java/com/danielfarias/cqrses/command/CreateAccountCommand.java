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
//Like a Data Transfert Object used to wrap data received and sent via REST APIs
public class CreateAccountCommand {
	
	@TargetAggregateIdentifier
    private UUID accountId;
	
    private BigDecimal initialBalance;
    
    private String owner;

}
