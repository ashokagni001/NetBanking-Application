package com.i2i.netbankingApplication.service;

import java.util.ArrayList;
import java.util.List;

import com.i2i.netbankingApplication.Constand.Constant;
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
	 * @param creditAccountNumber
	 *     creditAccountNumber of Transaction.
	 * @param ifscode
	 *     IFSCode of creditAccount.
	 * @param amount
	 *     amount of Transaction.
	 *      
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 */
	public String getTransactionDetail(String customerId, String creditAccountNumber, 
			String ifscode, double amount) throws DataBaseException {
		Account debitAccount = getAccountByCustomerId(customerId);
		Account creditAccount = transactionDao.retrieveAccountByNumber(creditAccountNumber);
		//verify the Credit Account available or not.
		if (creditAccount != null) {
			//verify the creditAccount IFSCode valid or not.
			if (creditAccount.getBranch().getIFSCode().equals(ifscode)) {
		        double currentAmount = debitAccount.getBalance();
		        double balanceAmount = (currentAmount - amount);
				//check the balanceAmount have or not.
				if (balanceAmount > Constant.INITIALIZEVARAILABLEVALUE) {
					debitAccount.setBalance(balanceAmount);
					return transactionDao.addTransaction(new CustomerTransaction(getLastTransactionId(), amount,
								"Request", debitAccount, creditAccount), debitAccount);
				} else {

					return "YOUR CREDIT AMOUNT VALUE IS MUST BE LESSER THAN CURRENT AMOUNT :Rs " + currentAmount;
				}
			} 
		} else {
			return "CREDITACCOUNTNUMBER INCORRECT";
		}
		return "YOUR TRANSACTION IS SCUCCESSED";
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
		int id = Constant.INITIALIZEVARAILABLEVALUE;
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
	 *     Return the list of CustomerTransactions.
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
	 *     Return to the lists of notifications.
     * </p>
     * 
	 * @return list
	 *     Return the list of Transactions.
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
     * <p>
     *     If request comes TransactionController, It will calling to retriveAllTransactions method in TransactionDao.
	 *     Return to the list of Customer Transactions.
	 * </p>
	 * 
     * @param customerId
     *     customerId of Customer to use retrieve the customer Account Number.
     * @param customerAccountNumber
     *     customerAccountNumber of Customer.
     *     
     * @return list
     *     Return the list of customer transactions.
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
			} else if (transaction.getCreditAccount().getAccountNumber().equals(customerAccountNumber)) {
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
	 *     Return to the object of Account.
     * </p>
     * 
     * @param accountNumber
     *     accountNumber of Account.
     *     
     * @return object
     *     return to the object of Account.
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
	 * @param creditAccountNumber
	 *     creditAccountNumber of Transaction.
	 * @param amount
	 *     amount of Transaction.
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 */
	public void transactionSuccess(int transactionId, String creditAccountNumber, Double amount, String userId)
			throws DataBaseException {
		Account creditAccount = transactionDao.retrieveAccountByNumber(creditAccountNumber);
		Customer approver = customerService.getCustomerById(userId);
	        double currentAmount = creditAccount.getBalance();
		double balanceAmount = (currentAmount + amount);
		creditAccount.setBalance(balanceAmount);
		transactionDao.transactionSuccess(creditAccount, transactionId,	approver);
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
	 * @param debitAccountNumber
	 *     debitAccountNumber of Transaction.
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
	    double currentAmount = debitAccount.getBalance();
		double balanceAmount = (currentAmount + amount);
		debitAccount.setBalance(balanceAmount);
		transactionDao.transactionFailure(debitAccount, transactionId, approver);
	}
	
	/**
	 * <p>
	 *     If request comes TransactionController, It will calling to getDateTransaction method in TransactionDao.
	 *     Return to the lists of Transaction by Date.
     * </p>
     * 
	 * @param fromDate
	 *     fromDate of Transaction.
	 * @param toDate
	 *     toDate of Transaction.
	 *    
	 * @return Object
	 *     Return CustomerTransaction Object.
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 */
	public List<CustomerTransaction> getDateTransaction(String fromDate, String toDate) 
			throws DataBaseException {
		return transactionDao.retriveTransactionByDate(fromDate, toDate);
	}
    
	/**
	 * <p>
	 *     If request comes TransactionController, It will calling to getCustomerById and getCustomerAccount method in TransactionService.
	 *     Return to the object of Account,
     * </p>
	 * 
	 * @param customerId
	 *     customerId of Customer to use get the CustomerAccountNumber.
	 *     
	 * @return Object
	 *     Return to the object of Account.
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 */
	public Account getAccountByCustomerId(String customerId) throws DataBaseException {
		return getCustomerAccount(customerService.getCustomerById(customerId).getAccountNumber());
	}
}
