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

/**
 * <p>
 *     Model class of CustomerTransaction. 
 *     It have getter method, setter method, default constructor and parameter constructor.
 *     Many to One mapping is established for Account model class.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-03
 */
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
    private Account creditAccount;
    
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
    
	/**
     * Default Constructor.
     * which create a instance of CustomerTransaction.
     */
	public CustomerTransaction() {
	}
	
	/**
     * parameter Constructor.
     * Passes parameters to the constructor and creates an instance of CustomerTransaction.
     */  
    public CustomerTransaction(int id, double amount,String status, Account debitAccount, Account creditAccount) {
		this.id = id;
		this.amount = amount;
		this.status = status;
		this.creditAccount = creditAccount;
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

	public Account getCreditAccount() {
		return creditAccount;
	}

	public void setCreditAccount(Account creditAccount) {
		this.creditAccount = creditAccount;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}