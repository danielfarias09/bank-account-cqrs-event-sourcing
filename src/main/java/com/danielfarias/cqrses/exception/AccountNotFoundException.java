package com.danielfarias.cqrses.exception;

import java.util.UUID;

public class AccountNotFoundException extends Exception {
	
	private static final long serialVersionUID = -3980735644395829893L;

	public AccountNotFoundException(UUID accountId) {
        super("Cannot found account number [" + accountId + "]");
    }

}
