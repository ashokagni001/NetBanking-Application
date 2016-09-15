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

/**
 * <p>
 *     Model class of Account. 
 *     It have getter method, setter method, default constructor and parameter constructor.
 *     Many to One mapping is established for Customer model class.
 *     One to One mapping is established for Branch model class.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-03
 */
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
	@JoinColumn(name="ifsc")
    private Branch branch;
    
    @Column(name = "balance")
    private double balance;
    
    @Column(name = "account_type")
    private String accountType;
    
    /**
     * Default Constructor.
     * which create a instance of Account.
     */
    public Account(){
    }
    
    /**
     * <p>
     *     parameter Constructor.
     *     Passes parameters to the constructor and creates an instance of Account.
     * </p>
     */           
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