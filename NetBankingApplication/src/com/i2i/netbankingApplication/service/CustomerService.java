package com.i2i.netbankingApplication.service;

import java.util.ArrayList;
import java.util.List;

import com.i2i.netbankingApplication.constantVariableUtil.ConstantVariableUtil;
import com.i2i.netbankingApplication.dao.CustomerDao;
import com.i2i.netbankingApplication.exception.CustomerDataException;
import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.model.Account;
import com.i2i.netbankingApplication.model.Address;
import com.i2i.netbankingApplication.model.Customer;
import com.i2i.netbankingApplication.model.CustomerTransaction;
import com.i2i.netbankingApplication.model.Role;
import com.i2i.netbankingApplication.model.UserRole;
import com.i2i.netbankingApplication.util.StringUtil;

/**
 * <p>
 *     When values comes from CustomerController.
 *     CustomerService operate passing value's to CustomerDao based on requset's from CustomerController.
 *     It can operate validation before send the value's in CustomerDao.
 *     It can be send the value's to CustomerController from CustomerDao.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created
 *
 */
public class CustomerService {
    CustomerDao customerDao = new CustomerDao();
    private TransactionService transactionService = new TransactionService();
    
    /**
     * <p>
     * customer object get from CustomerController.
     * In this customer is check already have bank account.If account exit given the customer id, password.   
     * If they not account return some use full message.
     * In this method we done customer id and password manual generation.
     * If customer add successfully return customer id and password.
     * 
     * @param customer
     * @throws DataBaseException
     * @throws CustomerDataException
     */
    public void getUser(Customer customer) throws DataBaseException, CustomerDataException {
    	Account account = customerDao.retrieveAccountByNumber(customer.getAccountNumber());
    	if (account == null) {
    		throw new CustomerDataException("YOUR ACCOUNT NUMBER IS NOT VALID");  
    	}
    	
    	if (account.getCustomer() != null) {
    		throw new CustomerDataException("YOUR ALREADY REGISTER THE NETBANKING..."); 
    	}
    	String customerId = "CUSI2I00" + String.valueOf(getLastCustomerId() + 1);
        if (StringUtil.isValidFormat(customer.getDob())) {
            throw new DataBaseException("YOUR FORMAT" + customer.getDob() +
                "FORMAT MUST 1/05/2000.INSERT VALID DOB..!!");  
        }
        
        int customerAge = StringUtil.calculateAge(customer.getDob());
        if (customerAge > ConstantVariableUtil.maxAgeLimit) {
        	throw new CustomerDataException("YOUR AGE IS NOT VALID");  
        }
        insertUserRole(customerId, "1");
        String password = "i2i" + String.valueOf((int)(Math.random()*9000));
        customerDao.insertUser(customer.getAccountNumber(), new Customer(customerId, customer.getName(), customerAge, customer.getDob(), 
            customer.getGender(), customer.getMobileNumber(), customer.getEmail(), password, customer.getAccountNumber(), "ACTIVE"));
    	 
    }
    
	public int getLastAddressId() throws DataBaseException {
    	int id = 0;
    	for (Address address : customerDao.retriveAllAddresses()) {
    		int temp = address.getAddressId();
    		if (id <= temp) {
    			id = temp;
    		}
    	}
    	return id;
    }
    
    public int getLastCustomerId() throws DataBaseException {
    	int lastCustomerId = 0;
    	if (customerDao.retriveAllCustomer().size() == 0) {
    		return lastCustomerId;
    	} else {
    		for (Customer customer : customerDao.retriveAllCustomer()) {
    			String id = customer.getCustomerId();
    		    int temp = Integer.parseInt(id.substring(6, id.length()));
                if (lastCustomerId <= temp) {
                	lastCustomerId = temp;
                }
    		}
    		return lastCustomerId;
    	}
    }
    
    public List<Customer> getAllCustomer() throws DataBaseException {
    	return customerDao.retriveAllCustomer();
	}
    
	public String getAddress(Address address) throws DataBaseException {
	    String customerId = "CUSI2I00" + String.valueOf(getLastCustomerId());
	    return customerDao.addAddress(customerId, new Address(getLastAddressId() + 1, address.getStreet(),
	        address.getCountry(), address.getCity(), address.getState() ,address.getPincode()));
    }

	public Customer getCustomerById(String customerId) throws DataBaseException {
        return customerDao.retrieveCustomerById(customerId); 
    }
	
	public Address getAddressById(int addressId) throws DataBaseException {
	    return customerDao.retrieveAddressById(addressId);
	}

	public List<CustomerTransaction> getMiniStatementByCustomerId(String customerId) throws DataBaseException {
		Customer customer = customerDao.retrieveCustomerById(customerId);
		if (customer != null) {
		    return transactionService.getCustomerMiniStatement(customer.getAccountNumber());
		} else {
			throw new DataBaseException("Enter valid id"); 
		}
	}
	
	public void insertUserRole(String customerId, String roleId) throws DataBaseException {
		Customer customer = customerDao.retrieveCustomerById(customerId);
		if (customer != null) {
			Role role = customerDao.retrieveRoleById(roleId);
			if (IfUserRoleExist(customerId, roleId)) {
			    customerDao.insertRole(new UserRole(getUserRoleLastId() + 1, customer, role));
			} else {
				throw new DataBaseException("ALREADY CUSTOMER ASSINED SAME ROLE");
			}
		} else {
			throw new DataBaseException("Please enter the valid userId");
		}
	}

	public List<Role> getAllRole() throws DataBaseException {
		return customerDao.retriveAllRole();
	}

	public boolean isRoleAvailable() throws DataBaseException {
		return (getAllRole().size() != 0);
	}
    
	public int getUserRoleLastId() throws DataBaseException {
		int lastId = 0;
		if (customerDao.retriveAllUserRole().size() == 0) {
			return lastId;
		} else {
		    for (UserRole userRole : customerDao.retriveAllUserRole()) {
			    int tempId = userRole.getId();
			    if (lastId <= tempId) {
				    lastId = tempId;
			    }
		    }
		return lastId;
		}
	}
	
	public boolean IfUserRoleExist(String customerId, String roleId) throws DataBaseException {
		if (customerDao.retriveAllUserRole().size() == 0) {
			return true;
		} else {
		    for (UserRole userRole : customerDao.retriveAllUserRole()) {
			    if ((userRole.getCustomer().getCustomerId().equals(customerId)) && (userRole.getRole().getRoleId().equals(roleId))) {
			    	return false;
			    }
		    }
		return true;
	    }
	}

	public boolean ifValidateUser(String customerId, String password) throws DataBaseException {
	    Customer customer = getCustomerById(customerId); 
	    if (customer == null) {
	    	return false;  
	    }
	    if(!(customer.getPassWord().equals(password))) {
	    	return false; 
	    }
	    return true;
	}

	public boolean checkIfRole(String customerId) throws DataBaseException {
		int userRoleCount = 0;
		for (UserRole userRole : customerDao.retriveAllUserRole()) {
			if (userRole.getCustomer().getCustomerId().equals(customerId)) {
				userRoleCount++;
			}
		}
	    if (userRoleCount == 1) {
			return false;
		}
		return true;
	}
}
			 
/*		}
		List<UserRole> userRoleList = customerDao.retriveAllUserRole();
		userRoleList.
		System.out.println(customerId);
        UserRole userRole = customerDao.retrieveUserRoleById(customerId);
        userRoleList = userRole.getCustomer();
        System.out.println("work");
	    return userRole.getRole().getRoleName().equals("APPROVER");
    }
} */
