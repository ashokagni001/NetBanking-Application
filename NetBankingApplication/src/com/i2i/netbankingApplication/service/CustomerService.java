package com.i2i.netbankingApplication.service;

import java.util.List;

import com.i2i.netbankingApplication.dao.CustomerDao;
import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.model.Address;
import com.i2i.netbankingApplication.model.Branch;
import com.i2i.netbankingApplication.model.Customer;

public class CustomerService {
    CustomerDao customerDao = new CustomerDao();
    
    public void getUser(Customer user) {
    	customerDao.insertUser(user);
    }

    public int getLastAddressId() throws DataBaseException {
		int temp;
    	int id = 0;
    	for (Address address : customerDao.retriveAllAddresses()) {
    		temp = address.getAddressId();
    		if (id <= temp) {
    			id = temp;
    		}
    	}
    	return id;
    }
    
    public int getLastIFSCode() throws DataBaseException {
    	int lastCustomerId = 0;
    	String id = " ";
        int temp;
    	if (customerDao.retriveAllCustomer().size() == 0) {
    		return lastCustomerId;
    	} else {
    		for (Customer customer : customerDao.retriveAllCustomer()) {
    		     id = customer.getCustomerId();
                 temp = Integer.parseInt(id.substring(6, id.length()));
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
    
	public void getAddress(Address address) throws DataBaseException {
	    String IFSCode = " ";
	    int tempIFS = getLastIFSCode();
	    if (tempIFS >= 0) {
	        IFSCode = "I2I0BK" + String.valueOf(tempIFS);
	    }
	    int id = getLastAddressId();
	    customerDao.addAddress(IFSCode,new Address(id+1, address.getStreet(),
	        address.getCountry(), address.getCity(), address.getState() ,address.getPincode()));
    }
} 
