package com.i2i.netBankingApplication.service;

import java.util.List;

import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.model.Branch;

public interface BranchManager extends GenericManager<Branch, Long> {
    
    /**
     * {@inheritDoc}
     */
    void addBranch(Branch branch) throws DataBaseException;
    
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
}
