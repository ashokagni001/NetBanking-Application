package com.i2i.netbankingapplication.dao;

import java.util.List;

import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.model.Account;
import com.i2i.netbankingapplication.model.CustomerTransaction;
import com.i2i.netbankingapplication.model.User;

/**
 * CustomerTransaction Data Access Object (GenericDao) interface.
 * 
 * @author team2
 *
 */
public interface CustomerTransactionDao extends GenericDao < CustomerTransaction, Long > {

    /**
     * Insert new customerTransaction;
     *
     */
    void insertTransaction(CustomerTransaction customerTransaction, Account debitAccount) throws DataBaseException;

    /**
     * Retrieve account by creditAccountNumber;
     *
     *@return Account
     */
    Account retrieveAccountByNumber(String creditAccountNumber) throws DataBaseException;

    /**
     * Retrieve all customerTransactions;
     *
     *@return List
     */
    List < CustomerTransaction > retriveAllTransactions() throws DataBaseException;

    /**
     * Update success transaction;
     *
     */
    void transactionSuccess(Account creditAccount, int transactionId, User user) throws DataBaseException;

    /**
     * Update failure transaction;
     *
     */
    void transactionFailure(Account debitAccount, int transactionId, User user) throws DataBaseException;
}
