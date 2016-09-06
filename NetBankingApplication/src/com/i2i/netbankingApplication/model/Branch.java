package com.i2i.netbankingApplication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "branch_detail")
public class Branch {
	@Id
    @Column(name = "ifsc")
	private String IFSCode;
	
	@Column(name = "email")
	private String emailId;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="address_id")
    //private String addressId;
	public Address address;
	
   // @OneToMany(cascade=CascadeType.PERSIST)
    //@JoinColumn(name="address_id")
    //public Set<Branch> branch = new HashSet<Branch>();
    
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
