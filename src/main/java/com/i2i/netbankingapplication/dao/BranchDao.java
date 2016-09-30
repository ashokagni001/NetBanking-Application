package com.i2i.netbankingapplication.dao;

import java.util.List;

import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.model.Account;
import com.i2i.netbankingapplication.model.Branch;

/**
 * <p>
 *   DataAccessObject(DAO) which is used to perform insert, update, retrieve, retrieve all 
 *   operations for branch and return to the responses.
 *   Creates session and transaction objects for each operation.
 * </p>
 *
 * @author Team-2
 * 
 * @created 2016-09-26
 */
public interface BranchDao extends GenericDao<Branch, Long> {
    
    /**
     * <p>
     *     Get the branch information from BranchManager.
     *     Add new branch information using database. 
     * </p>
     * 
     * @param branch
     *     branch holds the information of branch.
     *     
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    void insertBranch(Branch branch) throws DataBaseException ;
    
    /**
     * <p>
     *     Retrieve the account information corresponding ifscode using database.
     * </p>
     * 
     * @param ifscode
     *     ifscode of branch to view the corresponding record.
     *     
     * @return branch information corresponding ifscode.
     * 
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    Branch retrieveBranchByIFSCode(String IFSCode) throws DataBaseException ;
    
    /**
     * <p>
     *     Retrieve the list of branches using database.
     * </p>
     * 
     * @return list of branches.
     * 
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    List<Branch> retrieveBranches() throws DataBaseException ;
    
    /**
     * <p>
     *     Get the account information from BranchManager.
     *     Add new account information using database. 
     * </p>
     * 
     * @param account
     *     account holds the information of account.
     *     
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    void insertAccount(Account account) throws DataBaseException ;
    
    /**
     * <p>
     *     Retrieve the account information corresponding accountNumber using database.
     * </p>
     * 
     * @param accountNumber
     *     accountNumber of account used to view the corresponding record.
     *     
     * @return account information based on account number.
     * 
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    Account retrieveAccountByAccountNumber(String accountNumber) throws DataBaseException;
    
    /**
     * <p>
     *     Retrieve the list of accounts using database.
     * </p>
     * 
     * @return list of accounts
     * 
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    List<Account>retriveAllAccounts() throws DataBaseException ;
}
