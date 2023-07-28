package com.danielfarias.cqrses.service;

import static com.danielfarias.cqrses.service.ServiceUtils.formatUuid;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import com.danielfarias.cqrses.entity.BankAccount;
import com.danielfarias.cqrses.query.FindBankAccountQuery;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountQueryService {
	
	private QueryGateway queryGateway;
	
	private final EventStore eventStore;
	
	
	public CompletableFuture<BankAccount> findById(final String accountId){
		return this.queryGateway.query(
				new FindBankAccountQuery(formatUuid(accountId)), 
				ResponseTypes.instanceOf(BankAccount.class)
		);
		
	}
	
	public List<Object> listEventsForAccount(String accountId){
		return this.eventStore
				.readEvents(formatUuid(accountId).toString())
                .asStream()
                .map(Message::getPayload)
                .collect(Collectors.toList());
	}

}
