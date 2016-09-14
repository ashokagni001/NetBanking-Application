package com.i2i.netbankingApplication.service;

import java.util.ArrayList;
import java.util.List;

import com.i2i.netbankingApplication.dao.TransactionDao;
import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.model.Account;
import com.i2i.netbankingApplication.model.Customer;
import com.i2i.netbankingApplication.model.CustomerTransaction;

/**
 * <p>
 *     When request comes from TransactionController. Transaction service performs add or fetch or fetchAll transaction with model(UserTransaction),
 *     DAO(Transaction) and return the responses to transactionController.
 *     TransactionService operate passing value's to TransactionDao based on requset's from TransactionController.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-03.
 *
 */
public class TransactionService {
	private TransactionDao transactionDao = new TransactionDao();
	private CustomerService customerService = new CustomerService();
	
	/**
	 * <p> 
     *     Get the Transaction detail from TransactionController.
     *     It is passed to addTransaction method in TransactionDao and 
     *     If exception occurs return error message to TransactionController.
     * </p>
     * 
	 * @param debitAccountNumber
	 *     debitAccountNumber of Transaction. 
	 *     
	 * @param criditAccountNumber
	 *     criditAccountNumber of Transaction.
	 *     
	 * @param ifscode
	 *     ifscode of criditAccount.
	 *      
	 * @param amount
	 *     amount of Transaction.
	 *      
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 */
	public String getTransactionDetail(String debitAccountNumber, String criditAccountNumber, 
			String ifscode, double amount) throws DataBaseException {
		Account debitAccount = transactionDao.retrieveAccountByNumber(debitAccountNumber);
		Account criditAccount = transactionDao.retrieveAccountByNumber(criditAccountNumber);
		//verify the Debit Account available or not.
		if (debitAccount != null) {
			//verify the credit Account available or not.
			if (criditAccount != null) {
				//verify the criditAccount ifsc code valid or not.
				if (criditAccount.getBranch().getIFSCode().equals(ifscode)) {
				    double currentAmount = debitAccount.getBalance();
				    double balanceAmount = (currentAmount - amount);
				    //check the balanceAmount have or not.
				    if (balanceAmount > 0) {
						debitAccount.setBalance(balanceAmount);
						return transactionDao.addTransaction(new CustomerTransaction(getLastTransactionId(), amount,
								"Request", debitAccount, criditAccount), debitAccount);
					} else {
						return ("YOUR CREDIT AMOUNT VALUE IS MUST BE LESSER THAN CURRENT AMOUNT :Rs " + currentAmount);
					}
				} else {
					return ("CREDITACCOUNTNUMBER INCORRECT incorrect IFSC code ");
				}
			} else {
				return ("CREDITACCOUNTNUMBER INCORRECT");
			}
		} else {
			return ("YOUR DEBITACCOUNTNUMBER INCORRECT");
		}
	}
	
	/**
	 * <p> 
     *     Get the accountNumber from TransactionController.
     *     It is passed to retrieveAccountByNumber method in TransactionDao.
     *     Return the object of Account.
     * </p>
	 * 
	 * @param accountNumber
	 *     accountNumber of Account.
	 *     
	 * @return object.
	 *     Return the object of Account.
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 */
	public Account retrieveAccountByNumber(String accountNumber) throws DataBaseException {
		return transactionDao.retrieveAccountByNumber(accountNumber);
	}
	
	/**
	 * Calculate the new transaction Id.
	 *  
	 * @return id 
	 *     Return the new Transaction Id.
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 */
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
    
	/**
	 * <p> 
     *     Get the transactionId from TransactionController.
     *     It is passed to retrieveCustomerTransactionById method in TransactionDao and 
     *     Returns CustomerTransaction object to TransactionController.
     * </p>
     * 
	 * @param transactionId
	 *     Id of Transaction.
	 *     
	 * @return object
	 *     return object of CustomerTransaction.
	 * 
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 */
	public CustomerTransaction getTransactionById(String transactionId) throws DataBaseException {
        return transactionDao.retrieveCustomerTransactionById(transactionId); 
    }
	
	/**
	 * <p>
	 *     If request comes TransactionController, It will calling to retriveAllTransactions method in TransactionDao.
	 *     Return to the lists of Customer Transactions.
     * </p>
     * 
	 * @return List
	 *     Return the lists of CustomerTransaction.
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 */
	public List<CustomerTransaction> getAllTransaction() throws DataBaseException {
		List<CustomerTransaction> transactions = new ArrayList<CustomerTransaction>();
		for (CustomerTransaction transaction : transactionDao.retriveAllTransactions()) {
			    transactions.add(transaction);
		}
		return transactions;
	}
    
	/**
	 * <p>
	 *     If request comes TransactionController, It will calling to retriveAllTransactions method in TransactionDao.
	 *     Return to the lists of notification.
     * </p>
     * 
	 * @return list
	 *     Return the lists of Transaction.
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 */
    public List<CustomerTransaction> getAllNotification() throws DataBaseException {
		List<CustomerTransaction> transactions = new ArrayList<CustomerTransaction>();
		for (CustomerTransaction transaction : transactionDao.retriveAllTransactions()) {
			if (transaction.getStatus().equals("Request")) {
				transactions.add(transaction);
			} 
		}
		return transactions;
	}
    
    /**
     * 
     * @param customerAccountNumber
     *     customerAccountNumber of Customer.
     *     
     * @return list
     *     Return the customer transaction lists.
     *     
     * @throws DataBaseException
     *     If there is an error in the given data like BadElementException.
     */
   public List<CustomerTransaction> getCustomerMiniStatement(String customerId) throws DataBaseException {
		List<CustomerTransaction> transactions = new ArrayList<CustomerTransaction>();
		String customerAccountNumber = customerService.getCustomerById(customerId).getAccountNumber();
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
    /**
     * <p>
	 *     If request comes TransactionController,get the accountNumber form Transaction Controller.
	 *     It will calling to retrieveAccountDetail method in TransactionDao with accountNumber.
     * </p>
     * 
     * @param accountNumber
     *     accountNumber of Account.
     *     
     * @return object
     *     return to the object of Account,
     *     
     * @throws DataBaseException
     *     If there is an error in the given data like BadElementException.
     */
	public Account getCustomerAccount(String accountNumber) throws DataBaseException {
		return transactionDao.retrieveAccountByNumber(accountNumber);
	}
	
	/**
	 * <p>
	 *     If request comes TransactionController, get the transaction details and
	 *     it will calling to transactionSuccess method in TransactionDao
	 *     with transaction details.
     * </p>
     * 
	 * @param transactionId
	 *     id of Transaction.
	 *     
	 * @param criditAccountNumber
	 *     criditAccountNumber of Transaction.
	 *     
	 * @param amount
	 *     amount of Transaction.
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 */
	public void transactionSuccess(int transactionId, String criditAccountNumber, Double amount, String userId)
			throws DataBaseException {
		Account criditAccount = transactionDao.retrieveAccountByNumber(criditAccountNumber);
		Customer approver = customerService.getCustomerById(userId);
		if (criditAccount != null) {
			double currentAmount = criditAccount.getBalance();
			double balanceAmount = (currentAmount + amount);
			criditAccount.setBalance(balanceAmount);
			transactionDao.transactionSuccess(criditAccount, transactionId,	approver);
		} else {
			throw new DataBaseException("CREDIT ACCOUNT IS NOT AVAILABLE ");
		}
	}
	
	/**
	 * <p>
	 *     If request comes TransactionController, get the transaction details and
	 *     It will calling to transactionFailure method in TransactionDao
	 *     with transaction details.
     * </p>
     * 
	 * @param transactionId
	 *     id of Transaction.
	 *     
	 * @param debitAccountNumber
	 *     debitAccountNumber of Transaction.
	 *     
	 * @param amount
	 *     amount of Transaction.
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 */
	public void transactionFailure(int transactionId, String debitAccountNumber, Double amount, String userId)
			throws DataBaseException {
		Account debitAccount = transactionDao.retrieveAccountByNumber(debitAccountNumber);
		Customer approver = customerService.getCustomerById(userId);
		if (debitAccount != null) {
			double currentAmount = debitAccount.getBalance();
			double balanceAmount = (currentAmount + amount);
			debitAccount.setBalance(balanceAmount);
			transactionDao.transactionFailure(debitAccount, transactionId, approver);
		} else {
			throw new DataBaseException("CREDIT ACCOUNT IS NOT AVAILABLE ");
		}
	}
	
	/**
	 * <p>
	 *     If request comes TransactionController, It will calling to getDateTransaction method in TransactionDao.
	 *     Return to the lists of Transaction by Date.
     * </p>
     * 
	 * @param fromDate
	 *    fromDate of Transaction.
	 *    
	 * @param toDate
	 *    toDate of Transaction.
	 *    
	 * @return
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 */
	public List<CustomerTransaction> getDateTransaction(String fromDate, String toDate) throws DataBaseException {
		return transactionDao.retriveTransactionByDate(fromDate, toDate);
	}
}
