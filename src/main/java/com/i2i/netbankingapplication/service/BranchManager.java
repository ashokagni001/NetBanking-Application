package com.i2i.netbankingapplication.service;

import java.util.List;

import com.i2i.netbankingapplication.exception.BranchDataException;
import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.model.Account;
import com.i2i.netbankingapplication.model.Branch;

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
public interface BranchManager extends GenericManager<Branch, Long> {
    
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
    String addBranch(Branch branch) throws DataBaseException;
    
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
    Branch getBranchByIfscode(String ifscode) throws DataBaseException;
    
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
    List<Branch> getBranches() throws DataBaseException;
    
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
    String addAccount(Account account) throws DataBaseException;
    
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
    Account getAccountByAccountNumber(String accountNumber) throws DataBaseException;
    
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
    List<Account> viewAccountByBranch(String ifscode) throws BranchDataException, 
             DataBaseException;
}
