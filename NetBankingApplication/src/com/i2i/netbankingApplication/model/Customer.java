package com.i2i.netbankingApplication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * <p>
 * Model class of User. 
 * It have getter method, setter method,
 * default constructor and parameter constructor.
 * </p>
 * 
 * @author Ashok
 * 
 * @created 2016
 *
 */

@Entity
@Table(name = "user_detail")
public class Customer {
	@Id
    @Column(name = "user_id")
    private String customerId;
	
	@Column(name = "name")
    private String name;
	
	@Column(name = "age")
    private int age;
	
	@Column(name = "dob")
    private String dob;
	
	@Column(name = "gender")
    private String gender;
	
	@Column(name = "mobile_number")
    private long mobileNumber;
	
	@Column(name = "email")
    private String email;
	
	@Column(name = "password")
    private String passWord;
	
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "status")
    private String status;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="address_id")
	public Address address;
    
    public Customer() {
    }
    
    public Customer(String customerId, String name, int age, String dob, String gender,
            long mobileNumber, String email, String passWord, String accountNumber, String status) {
    	this.customerId = customerId;
    	this.name = name;
    	this.age = age;
    	this.dob = dob;
    	this.gender = gender;
    	this.mobileNumber = mobileNumber;
    	this.email = email;
    	this.passWord = passWord;
    	this.accountNumber = accountNumber;
    	this.status = status;
    }

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
    
}
    
	