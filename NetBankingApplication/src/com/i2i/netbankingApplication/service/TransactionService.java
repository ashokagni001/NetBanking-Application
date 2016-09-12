package com.i2i.netbankingApplication.service;

import java.util.ArrayList;
import java.util.List;

import com.i2i.netbankingApplication.dao.TransactionDao;
import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.model.Account;
import com.i2i.netbankingApplication.model.Customer;
import com.i2i.netbankingApplication.model.CustomerTransaction;

public class TransactionService {
	TransactionDao transactionDao = new TransactionDao();
	public String getTransactionDetail(String debitAccountNumber, String criditAccountNumber, 
			String ifscode, double amount) throws DataBaseException {
		Account debitAccount = transactionDao.retrieveAccountByNumber(debitAccountNumber);
		Account criditAccount = transactionDao.retrieveAccountByNumber(criditAccountNumber);
		if (debitAccount != null) {
			if (criditAccount != null) {
				if (criditAccount.getBranch().getIFSCode().equals(ifscode)) {
				    double currentAmount = debitAccount.getBalance();
				    double balanceAmount = (currentAmount - amount);
				    if (amount > 0) {
					    debitAccount.setBalance(balanceAmount);
				        return transactionDao.addTransaction(new CustomerTransaction(getLastTransactionId(), amount, "Request", debitAccount, criditAccount), debitAccount);
				    } else {
					    throw new DataBaseException("Your cridit amount value is must be lesser than current amount :Rs "+ currentAmount); 
				    }
				} 
			}
		} else {
			throw new DataBaseException("Your debitAccountNumber or criditAccountNumber incorrect"); 
		}
		return ("Please check your details");
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
    
	public CustomerTransaction getTransactionById(String transactionId) throws DataBaseException {
        return transactionDao.retrieveCustomerTransactionById(transactionId); 
    }
	
	public List<CustomerTransaction> getAllTransaction() throws DataBaseException {
		List<CustomerTransaction> transactions = new ArrayList<CustomerTransaction>();
		for (CustomerTransaction transaction : transactionDao.retriveAllTransactions()) {
			    transactions.add(transaction);
		}
		return transactions;
	}
    
    public List<CustomerTransaction> getAllNotification() throws DataBaseException {
		List<CustomerTransaction> transactions = new ArrayList<CustomerTransaction>();
		for (CustomerTransaction transaction : transactionDao.retriveAllTransactions()) {
			if (transaction.getStatus().equals("Request")) {
				transactions.add(transaction);
			} 
		}
		return transactions;
	}
    
    public List getCustomerMiniStatement(String customerAccountNumber) throws DataBaseException {
		List transactions = new ArrayList();
		for (CustomerTransaction transaction : transactionDao.retriveAllTransactions()) {
			if (transaction.getDebitAccount().getAccountNumber().equals(customerAccountNumber)) {
	        	transactions.add(transaction);
	        } else if (transaction.getCriditAccount().getAccountNumber().equals(customerAccountNumber)) {
	            if (!(transaction.getStatus().equals("Request"))) {
	                transactions.add(transaction);
	            }
	        }
		}
		return transactions;
	}
    		
	public Account getCustomerAccount(String accountNumber) throws DataBaseException {
		return transactionDao.retrieveAccountDetail(accountNumber);
	}
	
	public void transactionSuccess(int transactionId, String criditAccountNumber, Double amount) throws DataBaseException {
		Account criditAccount = transactionDao.retrieveAccountByNumber(criditAccountNumber);
		if (criditAccount != null) {
			 double currentAmount = criditAccount.getBalance();
			 double balanceAmount = (currentAmount + amount);
			 transactionDao.transactionSuccess(criditAccount.getAccountNumber(), balanceAmount, transactionId);
        } else {
	        throw new DataBaseException("CRIDIT ACCOUNT IS NOT AVAILABLE "); 
        }
	}
	
	public void transactionFailure(int transactionId, String debitAccountNumber, Double amount) throws DataBaseException {
		Account debitAccount = transactionDao.retrieveAccountByNumber(debitAccountNumber);
		if (debitAccount != null) {
			 double currentAmount = debitAccount.getBalance();
			 double balanceAmount = (currentAmount + amount);
			 transactionDao.transactionFailure(debitAccount.getAccountNumber(), balanceAmount, transactionId);
        } else {
	        throw new DataBaseException("CRIDIT ACCOUNT IS NOT AVAILABLE "); 
        }
	}
	
	public List<CustomerTransaction> getDateTransaction(String fromDate, String toDate) throws DataBaseException {
		return transactionDao.retriveTransactionByDate(fromDate, toDate);
	}
}