package com.i2i.netbankingApplication.service;

import java.util.List;

import com.i2i.netbankingApplication.dao.BranchDao;
import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.model.Address;
import com.i2i.netbankingApplication.model.Branch;

public class BranchService {
    BranchDao branchDao = new BranchDao();
    
    public void getBranch(String emailId) throws DataBaseException {
      	String IFSCode = "I2I0BK" + String.valueOf(getLastIFSCode() + 1);
    	branchDao.addBranch(new Branch(IFSCode, emailId));
    }
    
    public int getLastIFSCode() throws DataBaseException {
    	int lastIFSC = 0;
    	for (Branch branch : branchDao.retriveAllBranch()) {
    		String IFSC = branch.getIFSCode();
    		int temp = Integer.parseInt(IFSC.substring(6, IFSC.length()));
            if (lastIFSC <= temp) {
               	lastIFSC = temp;
            }
    	}
    	return lastIFSC;
    }

	public void deleteBranchById(String IFSCode) throws DataBaseException {
		branchDao.deleteBranchById(IFSCode);
	}
	
	public List<Branch> getAllBranch() throws DataBaseException {
    	return branchDao.retriveAllBranch();
	}

	public Branch getBranchById(String IFSCode) throws DataBaseException {
        return branchDao.retrieveBranchById(IFSCode); 
    }
	
	public int getLastAddressId() throws DataBaseException {
    	int id = 0;
    	for (Address address : branchDao.retriveAllAddresses()) {
    		int temp = address.getAddressId();
    		if (id <= temp) {
    			id = temp;
    		}
    	}
    	return id;
    }
    
	public void getAddress(Address address) throws DataBaseException {
	    String IFSCode = "I2I0BK" + String.valueOf(getLastIFSCode());
	    branchDao.addAddress(IFSCode,new Address(getLastAddressId() + 1, address.getStreet(),
	        address.getCountry(), address.getCity(), address.getState() ,address.getPincode()));
    }

	public Address getAddressById(int addressId) throws DataBaseException {
	    return branchDao.retrieveAddressById(addressId);
	}
}

