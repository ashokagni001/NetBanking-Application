package com.i2i.netbankingApplication.service;

import java.util.List;

import com.i2i.netbankingApplication.dao.BranchDao;
import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.model.Branch;

public class BranchService {
    BranchDao branchDao = new BranchDao();
    
    public void getBranch(Branch branch) throws DataBaseException {
      	int tempIFS = 1;
      	System.out.println(branch.getEmailId());
      	String IFSCode = " ";
      	//teamIFS = 0;
      	tempIFS = getLastIFSCode();
      	System.out.println(tempIFS);
        if (tempIFS >= 0) {
        	IFSCode = "I2I0BK" + String.valueOf(tempIFS + 1);
        } 
    	branchDao.addBranch(new Branch(IFSCode, branch.getEmailId()));
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

	public void deleteBranchById(String ifsc) throws DataBaseException {
		branchDao.deleteBranchById(ifsc);
	}
	
	public List<Branch> getAllBranch() throws DataBaseException {
    	return branchDao.retriveAllBranch();
	}

	public Branch getBranchById(String ifsc) throws DataBaseException {
        return branchDao.retrieveBranchById(ifsc); 
    }
}

