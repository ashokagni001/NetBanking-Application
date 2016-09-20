package com.i2i.netbankingApplication.model;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "beneficiary_detail")
public class Beneficiary {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinColumn(name = "beneficiary_account_number")
	private Customer beneficiaryAccountNumber;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinColumn(name = "customer_account_number")
	private Customer customerAccountNumber;
	
	public Beneficiary() {
	}
	
	public Beneficiary(Customer beneficiaryAccountNumber, Customer customerAccountNumber) {
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
		this.customerAccountNumber = customerAccountNumber;
	}
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getBeneficiaryAccountNumber() {
		return beneficiaryAccountNumber;
	}

	public void setBeneficiaryAccountNumber(Customer beneficiaryAccountNumber) {
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
	}

	public Customer getCustomerAccountNumber() {
		return customerAccountNumber;
	}

	public void setCustomerAccountNumber(Customer customerAccountNumber) {
		this.customerAccountNumber = customerAccountNumber;
	}
}
