package com.i2i.netbankingApplication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "transaction_detail")
public class CustomerTransaction {
	@Id
    @Column(name = "id")
    private int id;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="debit_account_number")
	private Account debitAccount;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="cridit_account_number")
    private Account criditAccount;
    
    @Column(name = "amount")
    private double amount;
    
    @Column(name = "date")
    private String date;
    
    @LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="user_id")
    private Customer customer;
    
	@Column(name = "status")
    private String status;
    
	public CustomerTransaction() {
	}
	
    public CustomerTransaction(int id, double amount,String status, Account debitAccount, Account criditAccount) {
		this.id = id;
		this.amount = amount;
		this.status = status;
		this.criditAccount = criditAccount;
		this.debitAccount = debitAccount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
    public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Account getDebitAccount() {
		return debitAccount;
	}

	public void setDebitAccount(Account debitAccount) {
		this.debitAccount = debitAccount;
	}

	public Account getCriditAccount() {
		return criditAccount;
	}

	public void setCriditAccount(Account criditAccount) {
		this.criditAccount = criditAccount;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}