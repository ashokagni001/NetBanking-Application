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
	@JoinColumn(name = "beneficiary_account_number")
	private User beneficiaryAccountNumber;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinColumn(name = "customer_account_number")
	private Account customerAccountNumber;
	
	@Column(name = "status")
	private String status;
	
	public Beneficiary() {
	}
	
	public Beneficiary(User beneficiaryAccountNumber, Account customerAccountNumber, String status) {
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
		this.customerAccountNumber = customerAccountNumber;
		this.status = status;
	}
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getBeneficiaryAccountNumber() {
		return beneficiaryAccountNumber;
	}

	public void setBeneficiaryAccountNumber(User beneficiaryAccountNumber) {
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
	}
	
	public Account getCustomerAccountNumber() {
		return customerAccountNumber;
	}

	public void setCustomerAccountNumber(Account customerAccountNumber) {
		this.customerAccountNumber = customerAccountNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}