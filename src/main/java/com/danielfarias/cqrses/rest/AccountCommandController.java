package com.danielfarias.cqrses.rest;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.danielfarias.cqrses.entity.BankAccount;
import com.danielfarias.cqrses.rest.dto.AccountCreationDTO;
import com.danielfarias.cqrses.rest.dto.MoneyAmountDTO;
import com.danielfarias.cqrses.service.AccountCommandService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/accounts")
@AllArgsConstructor
public class AccountCommandController {
	
	private final AccountCommandService accountCommandService;
	
	@PostMapping
    @ResponseStatus(value = CREATED)
    public CompletableFuture<BankAccount> createAccount(@RequestBody AccountCreationDTO creationDTO) {
        return this.accountCommandService.createAccount(creationDTO);
    }
	
	@PutMapping(value = "/credit/{accountId}")
    public CompletableFuture<String> creditMoneyToAccount(@PathVariable(value = "accountId") String accountId,
                                                          @RequestBody MoneyAmountDTO moneyCreditDTO) {
        return this.accountCommandService.creditMoneyToAccount(accountId, moneyCreditDTO);
    }

    @PutMapping(value = "/debit/{accountId}")
    public CompletableFuture<String> debitMoneyFromAccount(@PathVariable(value = "accountId") String accountId,
                                                           @RequestBody MoneyAmountDTO moneyDebitDTO) {
        return this.accountCommandService.debitMoneyFromAccount(accountId, moneyDebitDTO);
    }

}
