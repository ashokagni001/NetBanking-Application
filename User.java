package com.i2i.netbankingApplication.model;

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
    private String userId;
    private String name;
    private int age;
    private String dob;
    private String gender;
    private long mobileNumber;
    private String email;
    private String passWord;
    private String status;
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
    
	