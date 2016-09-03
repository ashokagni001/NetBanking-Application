package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address_detail")
public class Address {
	@Id
    @Column(name = "address_id", unique = true)
	private int addressId;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "pincode")
	private int pincode;
	
	public Address() {
	}
	
	public Address(String street, String country, String city, String state, int pincode) {
		this.street = street;
		this.country = country;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}
	
	public Address(int addressId, String street, String country, String city, String state, int pincode) {
		this.addressId = addressId;
		this.street = street;
		this.country = country;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
