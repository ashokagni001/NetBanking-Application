package com.i2i.netbankingapplication.dao;

import java.util.List;

import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.model.Account;
import com.i2i.netbankingapplication.model.CustomerTransaction;
import com.i2i.netbankingapplication.model.User;

public interface CustomerTransactionDao extends GenericDao<CustomerTransaction, Long> {

    public void insertTransaction(CustomerTransaction customerTransaction, Account debitAccount) throws DataBaseException; 

    public Account retrieveAccountByNumber(String creditAccountNumber) throws DataBaseException;

    public List<CustomerTransaction> retriveAllTransactions() throws DataBaseException;

    public void transactionSuccess(Account creditAccount, int transactionId, User user) throws DataBaseException;

    public void transactionFailure(Account debitAccount, int transactionId, User user) throws DataBaseException;
}
