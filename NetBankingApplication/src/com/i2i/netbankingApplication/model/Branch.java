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
/**
 * <p>
 *     Model class of Branch. 
 *     It have getter method, setter method, default constructor and parameter constructor.
 *     One to One mapping is established for Address model class.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-03
 *
 */
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
	
	/**
     * Default Constructor.
     * which create a instance of Branch.
     */
	public Branch() {
	}
	
	/**
     * parameter Constructor.
     *     Passes parameters to the constructor and creates an instance of Branch.
     */  
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
