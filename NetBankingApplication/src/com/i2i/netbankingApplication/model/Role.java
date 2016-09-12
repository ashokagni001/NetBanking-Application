package com.i2i.netbankingApplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 *     Model class of Role. 
 *     It have getter method, setter method,
 *     default constructor and parameter constructor.
 * </p>
 * 
 * @author Team-2
 * 
 * @created 2016-09-03
 *
 */
@Entity
@Table(name = "role_detail")
public class Role {
	
	@Id
	@Column(name = "role_id")
	private String roleId;
	
	@Column(name = "role_name")
	private String roleName;
	
	/**
     * Default Constructor.
     * which create a instance of Role.
     */
	public Role() {
	}
    
	/**
     * parameter Constructor.
     *     Passes parameters to the constructor and creates an instance of Role.
     */  
	public Role(String roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
	


