package com.i2i.netbankingApplication.service;

import com.i2i.netbankingApplication.dao.TransactionDao;
import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.model.Account;
import com.i2i.netbankingApplication.model.TransactionDetail;

public class TransactionService {
	TransactionDao transactionDao = new TransactionDao();
	
	public void getTransactionDetail(String debitAccountNumber, String criditAccountNumber, double amount) 
			throws DataBaseException {
		Account debitAccount = transactionDao.retrieveAccountByNumber(debitAccountNumber);
		Account criditAccount = transactionDao.retrieveAccountByNumber(criditAccountNumber);
		if (debitAccount != null) {
			if (criditAccount != null) {
				double currentAmount = debitAccount.getBalance();
				double balanceAmount = (currentAmount - amount);
				if (amount > 0) {
					debitAccount.setBalance(balanceAmount);
				    transactionDao.addTransaction(new TransactionDetail(getLastTransactionId(), amount, "Request"), debitAccount, criditAccount);
				} else {
					throw new DataBaseException("Your cridit amount value is must be lesser than current amount :Rs "+ currentAmount); 
				}
			}
		} else{
			throw new DataBaseException("Your debitAccountNumber or criditAccountNumber incorrect"); 
		}
	}
	
	public Account retrieveAccountByNumber(String accountNumber) throws DataBaseException {
		return transactionDao.retrieveAccountByNumber(accountNumber);
	}
	
	public int getLastTransactionId() throws DataBaseException {
		int id = 0;
    	for (TransactionDetail transactions : transactionDao.retriveAllTransactions()) {
    		int temp = transactions.getId();
    		if (id <= temp) {
    			id = temp;
    		}
    	}
    	return id + 1;
    }
}