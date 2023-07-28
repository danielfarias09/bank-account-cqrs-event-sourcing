package com.danielfarias.cqrses.projection;

import java.util.Optional;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.danielfarias.cqrses.entity.BankAccount;
import com.danielfarias.cqrses.event.AccountCreatedEvent;
import com.danielfarias.cqrses.event.MoneyCreditEvent;
import com.danielfarias.cqrses.event.MoneyDebitedEvent;
import com.danielfarias.cqrses.exception.AccountNotFoundException;
import com.danielfarias.cqrses.query.FindBankAccountQuery;
import com.danielfarias.cqrses.repository.BankAccountRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class BankAccountProjection {
	
	private final BankAccountRepository repository;
	
	//The Event is serving as a DTO wrapping
	@EventHandler
	public void on(AccountCreatedEvent event) {
		log.debug("Handling a Bank Account creation command {}", event.getId());
		BankAccount account = new BankAccount(
				event.getId(),
				event.getOwner(),
				event.getInitialBalance()
		);
		this.repository.save(account);
	}
	
	@EventHandler
	public void on(MoneyCreditEvent event) throws AccountNotFoundException {
		log.debug("Handling an Account Credit command {}", event.getId());
		Optional<BankAccount> optionalAccount = this.repository.findById(event.getId());
		if(optionalAccount.isPresent()) {
			BankAccount account = optionalAccount.get();
			account.setBalance(account.getBalance().add(event.getCreditAmount()));
			this.repository.save(account);
		}else {
			throw new AccountNotFoundException(event.getId());
		}
	}
	
	@EventHandler
    public void on(MoneyDebitedEvent event) throws AccountNotFoundException {
        log.debug("Handling an Account Debit command {}", event.getId());
        Optional<BankAccount> optionalBankAccount = this.repository.findById(event.getId());
        if (optionalBankAccount.isPresent()) {
            BankAccount bankAccount = optionalBankAccount.get();
            bankAccount.setBalance(bankAccount.getBalance().subtract(event.getDebitAmount()));
            this.repository.save(bankAccount);
        } else {
            throw new AccountNotFoundException(event.getId());
        }
    }
	
	@QueryHandler
	public BankAccount handle(FindBankAccountQuery query) {
	    log.debug("Handling FindBankAccountQuery query: {}", query);
	    return this.repository.findById(query.getAccountId()).orElse(null);
	}

}
