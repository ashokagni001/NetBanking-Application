package com.i2i.netbankingApplication.service;

import java.util.ArrayList;
import java.util.List;

import com.i2i.netbankingApplication.dao.TransactionDao;
import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.model.Account;
import com.i2i.netbankingApplication.model.Branch;
import com.i2i.netbankingApplication.model.CustomerTransaction;

public class TransactionService {
	TransactionDao transactionDao = new TransactionDao();
	
	public void getTransactionDetail(String debitAccountNumber, String criditAccountNumber, String ifscode, double amount) 
			throws DataBaseException {
		Account debitAccount = transactionDao.retrieveAccountByNumber(debitAccountNumber);
		Account criditAccount = transactionDao.retrieveAccountByNumber(criditAccountNumber);
		if (debitAccount != null) {
			if (criditAccount != null) {
				if (criditAccount.getBranch().getIFSCode().equals(ifscode)) {
				    double currentAmount = debitAccount.getBalance();
				    double balanceAmount = (currentAmount - amount);
				    if (amount > 0) {
					    debitAccount.setBalance(balanceAmount);
				        transactionDao.addTransaction(new CustomerTransaction(getLastTransactionId(), amount, "Request"), debitAccount, criditAccount);
				    } else {
					    throw new DataBaseException("Your cridit amount value is must be lesser than current amount :Rs "+ currentAmount); 
				    }
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
    	for (CustomerTransaction transactions : transactionDao.retriveAllTransactions()) {
    		int temp = transactions.getId();
    		if (id <= temp) {
    			id = temp;
    		}
    	}
    	return id + 1;
    }
    
    public List<CustomerTransaction> getAllTransaction() throws DataBaseException {
		List transactions = new ArrayList();
		for (CustomerTransaction transaction : transactionDao.retriveAllTransactions()) {
			if (transaction.getStatus().equals("Request")) {
				transactions.add(transaction);
			}
		}
		return transactions;
	}

	public Account getCustomerAccount(String accountNumber) throws DataBaseException {
		return transactionDao.retrieveAccountDetail(accountNumber);
	}

	public void transactionSuccess(int transactionId) {
	}
}