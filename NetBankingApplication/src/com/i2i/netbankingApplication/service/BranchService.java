package com.i2i.netbankingApplication.service;

import java.util.List;

import com.i2i.netbankingApplication.dao.BranchDao;
import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.model.Address;
import com.i2i.netbankingApplication.model.Branch;

public class BranchService {
    BranchDao branchDao = new BranchDao();
    
    public void getBranch(String emailId) throws DataBaseException {
      	String IFSCode = " ";
      	int tempIFS = getLastIFSCode();
        if (tempIFS >= 0) {
        	IFSCode = "I2I0BK" + String.valueOf(tempIFS + 1);
        } 
    	branchDao.addBranch(new Branch(IFSCode, emailId));
    }
    
    public int getLastIFSCode() throws DataBaseException {
    	int lastIFSC = 0;
    	String IFSC = " ";
        int temp;
    	if (branchDao.retriveAllBranch().size() == 0) {
    		return lastIFSC;
    	} else {
    		for (Branch branch : branchDao.retriveAllBranch()) {
    		     IFSC = branch.getIFSCode();
                 temp = Integer.parseInt(IFSC.substring(6, IFSC.length()));
                 if (lastIFSC <= temp) {
                	 lastIFSC = temp;
                 }
    		}
    		return lastIFSC;
    	}
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
		int temp;
    	int id = 0;
    	for (Address address : branchDao.retriveAllAddresses()) {
    		temp = address.getAddressId();
    		if (id <= temp) {
    			id = temp;
    		}
    	}
    	return id;
    }
    
	public void getAddress(Address address) throws DataBaseException {
	    String IFSCode = " ";
	    int tempIFS = getLastIFSCode();
	    if (tempIFS >= 0) {
	        IFSCode = "I2I0BK" + String.valueOf(tempIFS);
	    }
	    int id = getLastAddressId();
	    branchDao.addAddress(IFSCode,new Address(id+1, address.getStreet(),
	        address.getCountry(), address.getCity(), address.getState() ,address.getPincode()));
    }
}

