package com.i2i.netbankingapplication.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.netbankingapplication.Constants;
import com.i2i.netbankingapplication.dao.BranchDao;
import com.i2i.netbankingapplication.exception.BranchDataException;
import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.model.Account;
import com.i2i.netbankingapplication.model.Branch;
import com.i2i.netbankingapplication.service.BranchManager;

/**
 * <p>
 *     When request comes BranchManager performs add or fetch or fetchAll branch with model(Branch),
 *     DAO(Branch) and return the responses to BranchController.
 *     branchService operate passing value's to branchDao based on request.
 *     If method handle the TransactionCustomException. It is means our business logic custom exception.
 *     It service validate the business logics using Constants class.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-26.
 */
@Service("branchManager")
public class BranchManagerImpl extends GenericManagerImpl<Branch, Long> implements BranchManager {
    
    @Autowired
    BranchDao branchDao;
    
    /**
     * <p> 
     *     Get the branch from BranchController and pass to addBranch method in BranchDao.
     * </p>
     * 
     * @param branch
     *     branch holds the information of Branch.
     * @return status message (success or failure).
     * 
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    public String addBranch(Branch branch) throws DataBaseException {
        branch.setIFSCode(getIfscode());
        branchDao.insertBranch(branch);
        return "Branch Added Successfully.. IFSCode : " + branch.getIFSCode();
    }
    
    /**
     * <p>
     *     calculate the new branch ifscode using constants class.
     * </p>
     * 
     * @return new ifscode of branch.
     * 
     * @throws DataBaseException
     *      If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    private String getIfscode() throws DataBaseException {
        long id = 0;
        for (Branch branches : branchDao.retrieveBranches()) {
             id = branches.getId();
        }
        //calculate the new branch ifscode using constants class.
        //IFSC_PROFIX value = I2IBK0
        return Constants.IFSCODE_PROFIX + String.valueOf(id + 1);
    }
    
    /**
     * <p>
     *     Retrieves all branches from retriveAllBranch method in branchDao.
     *     Return the list of branches informations.
     * </p>
     * 
     * @return list of branches informations
     * 
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    public List<Branch> getBranches() throws DataBaseException {
        return branchDao.retrieveBranches();
    }
    
    /**
     * <p> 
     *     Get the ifscode of Branch
     *     Return the branch information based on branch ifscode.
     * </p>
     * 
     * @param ifscode
     *     ifscode of Branch used to retrieve branch.
     * 
     * @return branch information based on branch ifscode.
     *  
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    public Branch getBranchByIfscode(String ifscode) throws DataBaseException {
        return branchDao.retrieveBranchByIFSCode(ifscode);
    }
    
    /**
     * <p>
     *    This method add new account information based on branch ifscode.
     * </p>
     * 
     * @param account
     *     account holds the information of Account.
     *     
     * @return status message (success or failure).
     * 
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    public String addAccount(Account account) throws DataBaseException {
        branchDao.insertAccount(account);
        return "ACCOUNT ADDED SUCCESSFULLY";
    }
    
    /**
     * <p> 
     *     Get the accountNumber of Account.
     *     Return the account information based on accountNumber of Account.
     * </p>
     * 
     * @param accountNumber
     *     accountNumber of Account used to get the account information.
     *     
     * @return account information based on accountNumber of Account.
     * 
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    public Account getAccountByAccountNumber(String accountNumber) throws DataBaseException {
        return branchDao.retrieveAccountByAccountNumber(accountNumber);
    }
    
    /**
     * <p> 
     *     This method used to view account by branch.
     *     Return branch information based on branch ifscode.
     *     This method handle the our business logic exception(BranchDataException).
     * </p>
     * 
     * @param ifscode
     *      ifscode of Branch used to view account details.
     *       
     * @return branch information based on branch ifscode.
     * 
     * @throws BranchDataException
     *     It handle all the custom exception in NetBanking Application.
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    public List<Account> viewAccountByBranch(String ifscode) throws BranchDataException, DataBaseException{
        List<Account> accounts = new ArrayList<Account>();
        if (null != branchDao.retrieveBranchByIFSCode(ifscode)) {
            for (Account account : branchDao.retriveAllAccounts()) {
                if (account.getBranch().getIFSCode().equals(ifscode)) {
                    accounts.add(account);
                }
            }
            return accounts;
        } else {
            throw new BranchDataException("PLEASE ENTER VALID IFSCODE NUMBER"); 
        }
    }
}
