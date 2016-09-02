package com.i2i.netbankingApplication.model;

import javax.persistence.Column;
import javax.persistence.Id;

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
public class User {
	@Id
    @Column(name = "user_id", unique = true)
    private String userId;
	
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
	
	@Column(name = "mail")
    private String email;
	
	@Column(name = "passWord")
    private String passWord;
	
	@Column(name = "status")
    private String status;
	
	@Column(name = "address_id", nullable = true, columnDefinition = "TEXT" )
    private int addressId;
    
    public User() {
    }
    
    public User(String userId, String name, int age, String dob, String gender,
        long moblieNumber, String email, String passWord, String status) {
    	this.userId = userId;
    	this.name = name;
    	this.age = age;
    	this.dob = dob;
    	this.gender = gender;
    	this.mobileNumber = mobileNumber;
    	this.email = email;
    	this.passWord = passWord;
    	this.status = status;
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
    
}
    
	