package com.i2i.netbankingApplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * <p>
 * Model class of Branch.
 * It have getter and setter methods.
 * Default constructor and parameter constructor.
 * </p>
 * 
 * @author ashok.
 *
 */
@Entity
@Table(name = "branch")
public class Branch {
	@Id
    @Column(name = "IFSCode", unique = true)
	private String IFSCode;
	
	@Column(name = "email")
	private String emailId;
	
	@Column(name = "address_id", nullable = true, columnDefinition = "TEXT" )
	private int addressId;
	
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
	
	public int getAddressId() {
		return addressId;
	}
	
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getIFSCode() {
		return IFSCode;
	}

	public void setIFSCode(String IFSCode) {
		this.IFSCode = IFSCode;
	}
	
}
