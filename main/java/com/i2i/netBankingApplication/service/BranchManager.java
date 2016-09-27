package com.i2i.netBankingApplication.service;

import java.util.List;

import com.i2i.netBankingApplication.exception.BranchDataException;
import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.model.Account;
import com.i2i.netBankingApplication.model.Branch;

public interface BranchManager extends GenericManager<Branch, Long> {
    
    /**
     * {@inheritDoc}
     */
    String addBranch(Branch branch) throws DataBaseException;
    
    /**
     * {@inheritDoc}
     */
    void removeBranchByIFSCode(String IFSCode) throws DataBaseException;
    
    /**
     * {@inheritDoc}
     */
    Branch getBranchByIFSCode(String IFSCode) throws DataBaseException;
    
    /**
     * {@inheritDoc}
     */
    List getBranches() throws DataBaseException;
    
    /**
     * {@inheritDoc}
     */
    String addAccount(String accountNumber, double balance, String accountType, 
    		String IFSCode) throws DataBaseException;
    
    /**
     * {@inheritDoc}
     */ 
    List<Account> viewAccountByBranch(String IFSCode) throws BranchDataException, 
            DataBaseException;

}
