package com.i2i.netBankingApplication.dao;

import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.model.Account;
import com.i2i.netBankingApplication.model.CustomerTransaction;

public interface CustomerTransactionDao extends GenericDao<CustomerTransaction, Long> {

	public void insertTransaction(CustomerTransaction customerTransaction, Account debitAccount) throws DataBaseException; 

	public Account retrieveAccountByNumber(String creditAccountNumber) throws DataBaseException;

}
