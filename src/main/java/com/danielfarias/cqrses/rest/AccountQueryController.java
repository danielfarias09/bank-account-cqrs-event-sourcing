package com.danielfarias.cqrses.rest;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielfarias.cqrses.entity.BankAccount;
import com.danielfarias.cqrses.service.AccountQueryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/accounts")
@AllArgsConstructor
public class AccountQueryController {
	
	private final AccountQueryService accountQueryService;

    @GetMapping("/{accountId}")
    public CompletableFuture<BankAccount> findById(@PathVariable("accountId") String accountId) {
        return this.accountQueryService.findById(accountId);
    }

    @GetMapping("/{accountId}/events")
    public List<Object> listEventsForAccount(@PathVariable(value = "accountId") String accountId) {
        return this.accountQueryService.listEventsForAccount(accountId);
    }

}
