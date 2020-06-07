package com.dbs.backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EXCLUSION_ACCOUNT")
public class ExclusionAccount {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
	
	@Id
	private String accountNumber;

	@Override
	public String toString() {
		return "ExclusionAccount [accountNumber=" + accountNumber + "]";
	}
	
	public ExclusionAccount() {
	}
	
	public ExclusionAccount(String accountNumber) {
		super();
		this.accountNumber = accountNumber;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


}
