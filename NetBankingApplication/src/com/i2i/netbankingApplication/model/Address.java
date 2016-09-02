package com.i2i.netbankingApplication.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 
 * <p>
 * Model class of Address.
 * It have getter and setter methods.
 * Default constructor and parameter constructor.
 * </p>
 * 
 * @author sivashankar.
 *
 */
public class Address {
	@Id
    @Column(name = "address_id", unique = true)
	private String addressId;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "pincode")
	private String pincode;
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
    @JoinColumn(name = "address_id")
	private Set<Branch> branchs;
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
    @JoinColumn(name = "address_id")
	private Set<User> users;
	
	public Address() {
	}
	
	public Address(String addressId, String street, String city, String state, String pincode) {
		this.addressId = addressId;
		this.street = street;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public Set getBranchs() {
        return branchs;
    }
    
    public void setBranchs(Set branch) {
        this.branchs.addAll(branch);        
    }
    
    public void removeBranch(Branch branch) {
        this.branchs.remove(branch);
    }
    
    public Set getUsers() {
        return users;
    }
    
    public void setUsers(Set user) {
        this.users.addAll(user);        
    }
    
    public void removeUser(User user) {
        this.users.remove(user);
    }


}
