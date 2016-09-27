package com.i2i.netBankingApplication.service;

import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.exception.TransactionCustomException;
import com.i2i.netBankingApplication.model.CustomerTransaction;
import com.i2i.netBankingApplication.model.User;

public interface CustomerTransactionManager extends GenericManager<CustomerTransaction, Long> {

	String addTransactionDetail(User user, String creditAccountNumber, double parseDouble) 
			throws TransactionCustomException, DataBaseException;
}
