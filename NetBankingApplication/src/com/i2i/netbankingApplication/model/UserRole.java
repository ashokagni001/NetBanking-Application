package com.i2i.netbankingApplication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.i2i.netbankingApplication.model.Role;

/**
 * <p>
 *     Model class of UserRole. 
 *     It have getter method, setter method, default constructor and parameter constructor.
 *     Many to One mapping is established for Role and User model class
 * </p>
 * 
 * @author Team-2
 * 
 * @created 2016-09-03
 *
 */
@Entity
@Table(name = "user_role")
public class UserRole {
	@Id
	@Column(name = "id")
	private int id;
    
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	private Customer customer;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "role_id")
	private Role role;
    
	/**
     * Default Constructor.
     * which create a instance of UserRole.
     */
	public UserRole() {
	}
	
	/**
     * parameter Constructor.
     *     Passes parameters to the constructor and creates an instance of UserRole.
     */  
	public UserRole(int id, Customer customer, Role role) {
		this.id = id;
		this.customer = customer;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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