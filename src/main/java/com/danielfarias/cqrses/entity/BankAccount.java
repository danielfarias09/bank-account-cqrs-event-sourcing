package com.danielfarias.cqrses.entity;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BankAccount {
	
	@Id
	@Type(type = "uuid-char")
	private UUID id;
	
	private String owner;
	
	private BigDecimal balance;

}
