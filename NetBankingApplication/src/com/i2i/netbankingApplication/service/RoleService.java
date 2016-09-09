package com.i2i.netbankingApplication.service;

import java.util.List;

import com.i2i.netbankingApplication.dao.RoleDao;
import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.model.Customer;
import com.i2i.netbankingApplication.model.Role;
import com.i2i.netbankingApplication.model.UserRole;
import com.i2i.netbankingApplication.service.CustomerService;

public class RoleService {
	RoleDao roleDao = new RoleDao();
	CustomerService customerService = new CustomerService();

	public void insertRole(String customerId, String roleId) throws DataBaseException {
		Customer customer = customerService.getCustomerById(customerId);
		if (customer == null) {
			String id = "1";
			roleDao.insertRole(id, customerId, roleId);
			//roleDao.insertRole(new UserRole(id, customer, roleDao.retrieveRoleById(roleId)));
		} else {
			throw new DataBaseException("Please enter the valid userId");
		}
	}

	public List<Role> getAllRole() throws DataBaseException {
		return roleDao.retriveAllRole();
	}

	public boolean isRoleAvailable() throws DataBaseException {
		return (getAllRole().size() != 0);
	}

	public Role getRoleById(String Id) throws DataBaseException {
		return roleDao.retrieveRoleById(Id);
	}

	public int getLastId() throws DataBaseException {
		int lastId = 0;
		if (roleDao.retriveAllUserRole().size() == 0) {
			return lastId;
		} else {
			for (UserRole role : roleDao.retriveAllUserRole()) {
				String Id = role.getId();
				int temp = Integer.parseInt(Id);
				if (lastId <= temp) {
					lastId = temp;
				}
			}
			return lastId;
		}
	}

	public void deleteRoleBYId(String Id) throws DataBaseException {
		roleDao.deleteRole(Id);
	}

}
