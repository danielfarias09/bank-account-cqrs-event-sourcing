package com.danielfarias.cqrses.exception;

import java.math.BigDecimal;
import java.util.UUID;

public class InsufficientBalanceException extends Exception {
	
	private static final long serialVersionUID = 8111858672737802846L;

	public InsufficientBalanceException(UUID accountId, BigDecimal debitAmount) {
		super("Insufficient Balance: Cannot debit " + debitAmount + 
                " from account number [" + accountId.toString() + "]");
	}

}
