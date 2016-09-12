package com.i2i.netbankingApplication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "account_detail")
public class Account {
	@Id
    @Column(name = "account_number")
  	private String accountNumber;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="user_id")
    private Customer customer;
    
	@LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="ifscode")
    private Branch branch;
    
    @Column(name = "balance")
    private double balance;
    
    @Column(name = "account_type")
    private String accountType;
    
    public Account(){
    }
    
    public Account(String accountNumber, double balance, String accountType, Branch branch){
    	this.accountNumber = accountNumber;
    	this.balance = balance;
    	this.accountType = accountType;
    	this.branch = branch;
    }
    
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Branch getBranch() {
		return branch;
	}
	
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}