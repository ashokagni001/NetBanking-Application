package com.i2i.netbankingApplication.service;

import com.i2i.netbankingApplication.dao.TransactionDao;
import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.model.Account;
import com.i2i.netbankingApplication.model.TransactionDetail;

public class TransactionService {
	TransactionDao transactionDao = new TransactionDao();
	
	public void getTransactionDetail(String debitAccountNumber, String criditAccountNumber, double amount) 
			throws DataBaseException {
		try {
		TransactionDetail transactionDetail = null;
		if (ifAccountExist(debitAccountNumber)) {
			if (ifAccountExist(criditAccountNumber)) {
				double currentAmount = transactionDao.retrieveAccountByNumber(debitAccountNumber).getBalance();
				double balanceAmount = (currentAmount - amount);
				if (amount > 0) {
					Account debitAccount = transactionDao.retrieveAccountByNumber(debitAccountNumber);
					debitAccount.setBalance(balanceAmount);
					transactionDetail.setDebitAccountNumber(debitAccount);
					Account criditAccount = transactionDao.retrieveAccountByNumber(criditAccountNumber);
					transactionDetail.setDebitAccountNumber(criditAccount);
				    transactionDao.addTransaction(new TransactionDetail(getLastTransactionId() + 1, amount, "pending"));
				} else {
					throw new DataBaseException("Your cridit amount value is must be lesser than current amount :Rs "+ currentAmount); 
				}
			}
		} else{
			throw new DataBaseException("Your debitAccountNumber or criditAccountNumber incorrect"); 
		}
		} catch (DataBaseException e) {
            System.out.println("ENTER VALID DATA service" + e); 
        }
	}
	
	public boolean ifAccountExist(String accountNumber) throws DataBaseException {
		return (transactionDao.retrieveTransactionByAccoutNumber(accountNumber) != null);
	}
	
	public int getLastTransactionId() throws DataBaseException {
		int id = 0;
    	for (TransactionDetail transactions : transactionDao.retriveAllTransactions()) {
    		int temp = transactions.getId();
    		if (id <= temp) {
    			id = temp;
    		}
    	}
    	return id;
    }
	
	public TransactionDetail retrieveTransactionByAccoutNumber(String accountNumber) throws DataBaseException {
		return transactionDao.retrieveTransactionByAccoutNumber(accountNumber);
	}
}
