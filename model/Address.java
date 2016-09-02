package com.i2i.netbankingApplication.model;

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
	private String addressId;
	private String street;
	private String city;
	private String state;
	private String pincode;
	
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

}
