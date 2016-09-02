package com.i2i.netbankingApplication.model;

/**
 * 
 * <p>
 * Model class of Branch.
 * It have getter and setter methods.
 * Default constructor and parameter constructor.
 * </p>
 * 
 * @author sivashankar.
 *
 */
public class Branch {
	
	private String IFSCode;
	private String emailId;
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
