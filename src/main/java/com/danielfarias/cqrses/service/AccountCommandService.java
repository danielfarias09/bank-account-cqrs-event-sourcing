package com.danielfarias.cqrses.service;

import static com.danielfarias.cqrses.service.ServiceUtils.formatUuid;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import com.danielfarias.cqrses.command.CreateAccountCommand;
import com.danielfarias.cqrses.command.CreditMoneyCommand;
import com.danielfarias.cqrses.command.DebitMoneyCommand;
import com.danielfarias.cqrses.entity.BankAccount;
import com.danielfarias.cqrses.rest.dto.AccountCreationDTO;
import com.danielfarias.cqrses.rest.dto.MoneyAmountDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountCommandService {
	
	private final CommandGateway commandGateway;
	
	public CompletableFuture<BankAccount> createAccount(AccountCreationDTO creationDTO){
		UUID randomUUID = UUID.randomUUID();
		System.out.println("randomUUID: " + randomUUID);
		return this.commandGateway.send(new CreateAccountCommand(
				randomUUID,
				creationDTO.getInitialBalance(),
				creationDTO.getOwner()
		));
		
	}
	
	public CompletableFuture<String> creditMoneyToAccount(String accountId, MoneyAmountDTO moneyCreditDTO) {
		return this.commandGateway.send(
				new CreditMoneyCommand(formatUuid(accountId),moneyCreditDTO.getAmount())
		);
	}
	
	public CompletableFuture<String> debitMoneyFromAccount(String accountId, MoneyAmountDTO moneyDebitDTO) {
		return this.commandGateway.send(
				new DebitMoneyCommand(formatUuid(accountId), moneyDebitDTO.getAmount())
		);
	}
	
	

}
