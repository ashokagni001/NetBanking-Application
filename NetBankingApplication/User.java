package com.netbanking.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "user_detail")
public class User {
	@Id 
	@Column(name = "user_id")
    private String userid;
	
	@Column(name = "name")
    private String name;
	
	@Column(name = "age")
    private int age;
	
	@Column(name = "dob")
    private String dob;
	
	@Column(name = "gender")
    private String gender;
	
	@Column(name = "mobile_Number")
    private long mobileNumber;
	
	@Column(name = "mail_id")
    private String email;
	
	@Column(name = "passWord")
    private String passWord;
	
	@Column(name = "status")
    private String status;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    public Address address;
    
    public User() {
    }
    
    public User(String userid, String name, int age, String dob, String gender,
        long moblieNumber, String email, String passWord, String status) {
    	this.userid = userid;
    	this.name = name;
    	this.age = age;
    	this.dob = dob;
    	this.gender = gender;
    	this.mobileNumber = mobileNumber;
    	this.email = email;
    	this.passWord = passWord;
    	this.status = status;
    }

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String toString() {
		return "userid: " + userid + "name: " + name + "age: " + age + "dob: " + dob + "gender: " + gender +
				"moblieNumber: " + mobileNumber + "email: " + email + "passWord: " + passWord + "status: " + status; 
	}
    
}
    
	