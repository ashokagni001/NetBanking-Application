package com.i2i.netBankingApplication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.search.annotations.IndexedEmbedded;

/**
 * <p>
 *     Model class of UserTransaction. 
 *     It have getter method, setter method, default constructor and parameter constructor.
 *     Many to One mapping is established for Account model class.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-26
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
    @IndexedEmbedded
	private Account debitAccount;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="cridit_account_number")
    @IndexedEmbedded
    private Account creditAccount;
    
    @Column(name = "amount")
    private double amount;
    
    @Column(name = "date")
    private String date;
    
    @LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="user_id")
    @IndexedEmbedded
    private User user;
    
	@Column(name = "status")
    private String status;
    
	/**
     * Default Constructor.
     * which create a instance of UserTransaction.
     */
	public CustomerTransaction() {
	}
	
	/**
     * parameter Constructor.
     * Passes parameters to the constructor and creates an instance of UserTransaction.
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
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}