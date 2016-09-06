package com.i2i.netbankingApplication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_detail")
public class TransactionDetail {
	@Id
    @Column(name = "id")
    private int id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="debit_account_number")
	private Account debitAccountNumber;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="cridit_account_number")
    private Account criditAccountNumber;
    
    @Column(name = "amount")
    private double amount;
    
    @Column(name = "date")
    private String date;
    

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="user_id")
    private Customer customer;
    
    public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "status")
    private String status;
    
    public TransactionDetail(int id, double amount,String status) {
		this.id = id;
		this.amount = amount;
		this.status = status;
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

	public Account getDebitAccountNumber() {
		return debitAccountNumber;
	}

	public void setDebitAccountNumber(Account debitAccountNumber) {
		this.debitAccountNumber = debitAccountNumber;
	}

	public Account getCriditAccountNumber() {
		return criditAccountNumber;
	}

	public void setCriditAccountNumber(Account criditAccountNumber) {
		this.criditAccountNumber = criditAccountNumber;
	}
}
