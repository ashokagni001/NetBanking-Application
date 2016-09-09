package com.i2i.netbankingApplication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.i2i.netbankingApplication.model.Role;

@Entity
@Table(name = "user_detail")
public class UserRole {
	@Id
	@Column(name = "id")
	private String id;

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	private Customer customer;


	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "role_id")
	private Role role;


	public UserRole(String id, Customer customer, Role role) {
		super();
		this.id = id;
		this.customer = customer;
		this.role = role;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}

}