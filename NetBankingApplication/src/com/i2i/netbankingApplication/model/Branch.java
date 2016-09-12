package com.i2i.netbankingApplication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "branch_detail")
public class Branch {
	@Id
    @Column(name = "ifscode")
	private String IFSCode;
	
	@Column(name = "email")
	private String emailId;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="address_id")
	public Address address;
	
	public Branch() {
	}
	
	public Branch(String IFSCode, String emailId) {
		this.IFSCode = IFSCode;
		this.emailId = emailId;
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getIFSCode() {
		return IFSCode;
	}

	public void setIFSCode(String IFSCode) {
		this.IFSCode = IFSCode;
	}
	
}
