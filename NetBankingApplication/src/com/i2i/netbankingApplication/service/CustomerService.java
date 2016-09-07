package com.i2i.netbankingApplication.service;

import java.util.List;

import com.i2i.netbankingApplication.dao.CustomerDao;
import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.model.Address;
import com.i2i.netbankingApplication.model.Branch;
import com.i2i.netbankingApplication.model.Customer;
import com.i2i.netbankingApplication.util.StringUtil;

public class CustomerService {
    CustomerDao customerDao = new CustomerDao();
    
    public void getUser(Customer customer) throws DataBaseException {
      	String customerId = "I2I0BK" + String.valueOf(getLastCustomerId() + 1);
        if (StringUtil.isValidFormat(customer.getDob())) {
            throw new DataBaseException("YOUR FORMAT" + customer.getDob() +
                "FORMAT MUST 1/05/2000.INSERT VALID DOB..!!");  
        }
        int customerAge = StringUtil.calculateAge(customer.getDob());
        String password = "i2i" + String.valueOf((int)(Math.random()*9000));
    	customerDao.insertUser(new Customer(customerId, customer.getName(), customerAge, customer.getDob(), 
            customer.getGender(), customer.getMobileNumber(), customer.getEmail(), password, customer.getAccountNumber(), "Request"));
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
        for (Customer customer : customerDao.retriveAllCustomer()) {
    		String id = customer.getCustomerId();
    		int temp = Integer.parseInt(id.substring(6, id.length()));
            if (lastCustomerId <= temp) {
               	lastCustomerId = temp;
            }
    	}
		return lastCustomerId;
    }
    
    public List<Customer> getAllCustomer() throws DataBaseException {
    	return customerDao.retriveAllCustomer();
	}
    
	public void getAddress(Address address) throws DataBaseException {
	    String customerId = "I2I0BK" + String.valueOf(getLastCustomerId());
	    customerDao.addAddress(customerId, new Address(getLastAddressId() + 1, address.getStreet(),
	        address.getCountry(), address.getCity(), address.getState() ,address.getPincode()));
    }

	public Customer getCustomerById(String customerId) throws DataBaseException {
        return customerDao.retrieveCustomerById(customerId); 
    }
	
	public Address getAddressById(int addressId) throws DataBaseException {
	    return customerDao.retrieveAddressById(addressId);
	}
} 
