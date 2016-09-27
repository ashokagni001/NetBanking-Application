package com.i2i.netBankingApplication.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.netBankingApplication.Constants;
import com.i2i.netBankingApplication.dao.BranchDao;
import com.i2i.netBankingApplication.exception.BranchDataException;
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
    public String addBranch(Branch branch) throws DataBaseException {
        branch.setIFSCode(getIFSCode());
        branchDao.insertBranch(branch);
        return "Branch Added Successfully.. IFSCode : " + branch.getIFSCode();
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
    
    /**
	 * <p>
	 *    This method verify the branch IFSCode exist or not.
	 *    If branch exist add new account.
	 *    Otherwise, return the error message.
	 * </p>
	 * 
	 * @param accountNumber
	 *     accountNumber of Account.
	 * @param balance
	 *     balance of Account.
	 * @param accounttype
	 *     accountType of Account.
	 * @param ifsc
	 *     IfsCode of Account.
	 *     
	 * @throws DataBaseException
	 *     It handle all the custom exception in NetBanking application.
	 */
	public String addAccount(Account account) throws DataBaseException {
		//verify the branch id exist or not.
		branchDao.insertAccount(account);
		return ("ACCOUNT ADDED SUCCESSFULLY");
	}
    
	public Account getAccountByAccountNumber(String accountNumber) throws DataBaseException {
	    return branchDao.retrieveAccountByAccountNumber(accountNumber);
	}
	
	/**
     * <p> 
     *     This method use to view account by branch.
     *     This method get the branch IFSC from branch controller. 
     * </p>
     * 
     * @param IFSCode
     *     IFSCode of Account to use view list of accounts.
     *     
     * @return List.
     *     return the list of accounts.
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking application.
     * @throws BranchDataException 
     *     It handle all the custom exception.
     */
    public List<Account> viewAccountByBranch(String IFSCode) throws DataBaseException, BranchDataException {
        List<Account> accounts = new ArrayList<Account>();
        if (null != branchDao.retrieveBranchByIFSCode(IFSCode)) {
            for (Account account : branchDao.retriveAllAccounts()) {
                if (account.getBranch().getIFSCode().equals(IFSCode)) {
                    accounts.add(account);
                }
            }
            return accounts;
        } else {
            throw new BranchDataException("PLEASE ENTER VALID IFSC NUMBER"); 
        }
    }
}
