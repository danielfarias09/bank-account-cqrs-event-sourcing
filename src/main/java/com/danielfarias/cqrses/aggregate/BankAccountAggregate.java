package com.danielfarias.cqrses.aggregate;

import java.math.BigDecimal;
import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.danielfarias.cqrses.command.CreateAccountCommand;
import com.danielfarias.cqrses.command.CreditMoneyCommand;
import com.danielfarias.cqrses.command.DebitMoneyCommand;
import com.danielfarias.cqrses.event.AccountCreatedEvent;
import com.danielfarias.cqrses.event.MoneyCreditEvent;
import com.danielfarias.cqrses.event.MoneyDebitedEvent;
import com.danielfarias.cqrses.exception.InsufficientBalanceException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Aggregate //informs Axon’s auto configurer for Spring that this class is an Aggregate instance.
public class BankAccountAggregate {
	
	@AggregateIdentifier // identifies the field as the identifier of the Aggregate.
	private UUID id;
	
	private BigDecimal balance;
	
	private String owner;
	
	@CommandHandler 
	public BankAccountAggregate(CreateAccountCommand command) {
		AggregateLifecycle.apply(
				new AccountCreatedEvent(
						command.getAccountId(), 
						command.getInitialBalance(), 
						command.getOwner()
				)
		);
	}
	
	@EventSourcingHandler //handler for Events generated by that Aggregate
	public void on(AccountCreatedEvent event) {
		this.id = event.getId();
		this.owner = event.getOwner();
		this.balance = event.getInitialBalance();
	}
	
	@CommandHandler 
	public void handle(CreditMoneyCommand command) {
		AggregateLifecycle.apply(
				new MoneyCreditEvent(
						command.getAccountId(), 
						command.getCreditAmount()
				)
		);
	}
	
	@EventSourcingHandler
	public void on(MoneyCreditEvent event) {
		this.balance = this.balance.add(event.getCreditAmount());
	}
	
	@CommandHandler
    public void handle(DebitMoneyCommand command) {
        AggregateLifecycle.apply(
                new MoneyDebitedEvent(
                        command.getAccountId(),
                        command.getDebitAmount()
                )
        );
    }
	
	@EventSourcingHandler
    public void on(MoneyDebitedEvent event) throws InsufficientBalanceException {
        if (this.balance.compareTo(event.getDebitAmount()) < 0) {
            throw new InsufficientBalanceException(event.getId(), event.getDebitAmount());
        }
        this.balance = this.balance.subtract(event.getDebitAmount());
    }

}
