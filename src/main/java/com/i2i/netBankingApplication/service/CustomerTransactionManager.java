package com.i2i.netBankingApplication.service;

import java.util.List;

import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.exception.TransactionCustomException;
import com.i2i.netBankingApplication.model.CustomerTransaction;
import com.i2i.netBankingApplication.model.User;

public interface CustomerTransactionManager extends GenericManager<CustomerTransaction, Long> {

	String addTransaction(User user, String creditAccountNumber, double parseDouble) 
			throws TransactionCustomException, DataBaseException;

	List getAllNotifications() throws TransactionCustomException, DataBaseException;

	void transactionSuccess(int transactionId, String creditAccountNumber, Double amount, User user) throws DataBaseException;

	void transactionFailure(int transactionId, String debitAccountNumber, Double amount, User user) throws DataBaseException;

	List getCustomerMiniStatements(User user) throws DataBaseException;

	List getAllTransactions() throws DataBaseException;

}