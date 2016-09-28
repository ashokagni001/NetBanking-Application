package com.i2i.netbankingapplication.dao;

import java.util.List;

import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.model.Account;
import com.i2i.netbankingapplication.model.Branch;

/**
 * Branch Data Access Object (GenericDao) interface.
 *
 * @author team2
 */
public interface BranchDao extends GenericDao < Branch, Long > {

    /**
     * Insert new branch;
     *
     */
    void insertBranch(Branch branch) throws DataBaseException;

    /**
     * Remove branch by IFSCode;
     *
     */
    void removeBranchByIFSCode(String IFSCode) throws DataBaseException;

    /**
     * Retrieve branch by IFSCode;
     *
     *@return Branch
     */
    Branch retrieveBranchByIFSCode(String IFSCode) throws DataBaseException;

    /**
     * Retrieve all branches;
     *
     *@return List
     */
    List < Branch > retrieveBranches() throws DataBaseException;

    /**
     * Insert new account;
     *
     */
    void insertAccount(Account account) throws DataBaseException;

    /**
     * Retrieve account by accountNumber;
     *
     *@return Account
     */
    Account retrieveAccountByAccountNumber(String accountNumber) throws DataBaseException;

    /**
     * Retrieve all accounts;
     *
     *@return List
     */
    List < Account > retriveAllAccounts() throws DataBaseException;
}
