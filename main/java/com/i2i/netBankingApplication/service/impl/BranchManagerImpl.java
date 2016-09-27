package com.i2i.netBankingApplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.netBankingApplication.Constants;
import com.i2i.netBankingApplication.dao.BranchDao;
import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.model.Account;
import com.i2i.netBankingApplication.model.Branch;
import com.i2i.netBankingApplication.service.BranchManager;

@Service("branchManager")
public class BranchManagerImpl extends GenericManagerImpl<Branch, Long> implements BranchManager {
    
    @Autowired
    BranchDao branchDao;

    @Autowired
    public BranchManagerImpl(BranchDao branchDao) {
        super(branchDao);
        this.branchDao = branchDao;
    }
    
    /**
     * {@inheritDoc}
     */
    public void addBranch(Branch branch) throws DataBaseException {
        branch.setIFSCode(getIFSCode());
        branchDao.insertBranch(branch);
    }
    
    public String getIFSCode() throws DataBaseException {
        long id = 0;
        for (Branch branches : branchDao.retrieveBranches()) {
             id = branches.getId();
        }
        return (Constants.IFSCODE_PROFIX + String.valueOf(id + 1));
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Branch> getBranches() throws DataBaseException {
        return branchDao.retrieveBranches();
    }

    /**
     * {@inheritDoc}
     */
    public Branch getBranchByIFSCode(String IFSCode) throws DataBaseException {
        return branchDao.retrieveBranchByIFSCode(IFSCode);
    }
    
    /**
     * {@inheritDoc}
     */
    public void removeBranchByIFSCode(String IFSCode) throws DataBaseException {
        branchDao.removeBranchByIFSCode(IFSCode);
    }
    
}
