package com.i2i.netBankingApplication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "beneficiaries_detail")
public class Beneficiary {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User beneficiaryUser;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinColumn(name = "customer_account_number")
	private Account userAccountNumber;
	
	@Column(name = "status")
	private String status;
	
	public Beneficiary() {
	}
	
	public Beneficiary(User beneficiaryUser, Account userAccountNumber, String status) {
		this.beneficiaryUser = beneficiaryUser;
		this.userAccountNumber = userAccountNumber;
		this.status = status;
	}
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getBeneficiaryUser() {
		return beneficiaryUser;
	}

	public void setBeneficiaryUser(User beneficiaryUser) {
		this.beneficiaryUser = beneficiaryUser;
	}
	
	public Account getUserAccountNumber() {
		return userAccountNumber;
	}

	public void setUserAccountNumber(Account userAccountNumber) {
		this.userAccountNumber = userAccountNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}