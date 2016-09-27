package com.i2i.netBankingApplication.dao;

import java.util.List;

import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.model.Account;
import com.i2i.netBankingApplication.model.Branch;

public interface BranchDao extends GenericDao<Branch, Long> {
	
    void insertBranch(Branch branch) throws DataBaseException ;
	 
    void removeBranchByIFSCode(String IFSCode) throws DataBaseException ;
	 
    Branch retrieveBranchByIFSCode(String IFSCode) throws DataBaseException ;
    
    List<Branch> retrieveBranches() throws DataBaseException ;
    
}
