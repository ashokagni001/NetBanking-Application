package com.i2i.netbankingapplication.service;

import java.util.List;

import com.i2i.netbankingapplication.exception.BranchDataException;
import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.model.Account;
import com.i2i.netbankingapplication.model.Branch;

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
    String addAccount(Account account) throws DataBaseException;
    
    Account getAccountByAccountNumber(String accountNumber) throws DataBaseException;
    
    List<Account> viewAccountByBranch(String IFSCode) throws BranchDataException, 
    DataBaseException;
}
