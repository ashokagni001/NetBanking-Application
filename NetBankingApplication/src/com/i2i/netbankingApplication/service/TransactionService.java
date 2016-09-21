package com.i2i.netbankingApplication.service;

import java.util.ArrayList;
import java.util.List;

import com.i2i.netbankingApplication.constant.Constant;
import com.i2i.netbankingApplication.dao.TransactionDao;
import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.exception.TransactionCustomException;
import com.i2i.netbankingApplication.model.Account;
import com.i2i.netbankingApplication.model.Beneficiary;
import com.i2i.netbankingApplication.model.Customer;
import com.i2i.netbankingApplication.model.CustomerTransaction;
import com.i2i.netbankingApplication.model.UserRole;
import com.i2i.netbankingApplication.util.StringUtil;

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
	 *     It handle all the custom exception in NetBanking Application.
	 */
	public String addTransactionDetail(String customerId, String creditAccountNumber, 
			double amount) throws TransactionCustomException, DataBaseException {
		Account debitAccount = getAccountByCustomerId(customerId);
		double currentAmount = debitAccount.getBalance();
		double balanceAmount = (currentAmount - amount);
		//check the balanceAmount have or not.
		if (balanceAmount > Constant.INITIALIZEVARAILABLEVALUE) {
		    debitAccount.setBalance(balanceAmount);
			transactionDao.insertTransaction(new CustomerTransaction(getLastTransactionId(), amount,
					"Request", debitAccount, transactionDao.retrieveAccountByNumber(creditAccountNumber)), debitAccount);
			return ("YOUR TRANSACTION DETAIL SEND OUR TRANSACTION APPROVER PLEASE WAIT");
		} else {
			throw new TransactionCustomException("YOUR CREDIT AMOUNT VALUE IS MUST BE LESSER THAN CURRENT AMOUNT :Rs " + currentAmount );
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
	 *     accountNumber of Account to used for retrieve one account.
	 *     
	 * @return Account.
	 *     Return Account details.
	 *     
	 * @throws DataBaseException
	 *     It handle all the custom exception in NetBanking Application.
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
	 *     It handle all the custom exception in NetBanking Application.
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
	 *     Id of Transaction to view transaction detail.
	 *     
	 * @return CustomerTransaction
	 *     Return Customer Transaction details.
	 * 
	 * @throws DataBaseException
	 *     It handle all the custom exception in NetBanking Application.
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
	 *     It handle all the custom exception in NetBanking Application.
	 */
	public List<CustomerTransaction> getAllTransactions() throws DataBaseException {
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
	 *     It handle all the custom exception in NetBanking Application.
	 */
    public List<CustomerTransaction> getAllNotifications() throws DataBaseException {
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
     *     It handle all the custom exception in NetBanking Application.
     */
   public List<CustomerTransaction> getCustomerMiniStatements(String customerId) throws DataBaseException {
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
     *     It handle all the custom exception in NetBanking Application.
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
	 *     It handle all the custom exception in NetBanking Application.
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
	 *     It handle all the custom exception in NetBanking Application.
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
	 * @return CustomerTransaction
	 *     Return CustomerTransaction detail by specified dates.
	 *     
	 * @throws DataBaseException
	 *     It handle all the custom exception in NetBanking Application.
	 */
	public List<CustomerTransaction> getDateTransaction(String fromDate, String toDate) 
			throws DataBaseException {
		if (StringUtil.isValidDateFormat(fromDate) && StringUtil.isValidDateFormat(toDate)) {
            throw new DataBaseException("YOUR FORMAT " + fromDate + " & " + toDate + 
                " FORMAT MUST 2000-05-21.INSERT VALID DATE..!!");  
        }
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
	 *     It handle all the custom exception in NetBanking Application.
	 */
	public Account getAccountByCustomerId(String customerId) throws DataBaseException {
		return getCustomerAccount(customerService.getCustomerById(customerId).getAccountNumber());
	}
    
	public String addBeneficiaryAccount(String customerId, String accountNumber, String IFSCode) throws TransactionCustomException, DataBaseException {
		Account customerAccount = getCustomerAccount(accountNumber);
		if(null != customerAccount) {
			if(customerAccount.getBranch().getIFSCode().equals(IFSCode)) {
				Customer beneficiaryCustomer = customerService.getCustomerById(customerId);
				transactionDao.insertBeneficiaryAccount(new Beneficiary(beneficiaryCustomer, customerAccount, "request"));
				return "YOUR ACCOUNT ADDED SUCCESSFULLY";
			}
			throw new TransactionCustomException("YOUR IFSCODE IS WORNG " + IFSCode);
		}
		throw new TransactionCustomException("YOUR ACCOUNT NUMBER IS WORNG " + accountNumber);
	}
	
	public List<Beneficiary> getBeneficiaryAccountByCustomerId(String customerId) throws TransactionCustomException, DataBaseException {
	    List<Beneficiary> customerBeneficiary = new ArrayList<Beneficiary>();
	    for(Beneficiary beneficiary : (customerService.getCustomerById(customerId).getBeneficiary())) {
		    if (!((beneficiary.getStatus().equals("request")) | (beneficiary.getStatus().equals("Failure")))) {
			    customerBeneficiary.add(beneficiary);
		    }
		    if(null == customerBeneficiary) {
			    throw new TransactionCustomException("YOUR BENEFICIARY ACCOUNT NOT AVAILABLE." + customerId +"INSERT NEW BENEFICIARY ACCOUNT");
		    }
	    }
	    return customerBeneficiary;
	}

	public List<Beneficiary> getAllBeneficiaries() throws DataBaseException {
		List<Beneficiary> beneficiaries = new ArrayList<Beneficiary>();
		for (Beneficiary beneficiary : transactionDao.retriveAllBeneficiaries()) {
			if (beneficiary.getStatus().equals("request")) {
				beneficiaries.add(beneficiary);
			} 
		}
		return beneficiaries;
	}

	public void beneficiaryAccountActive(int beneficiaryId) throws DataBaseException {
		transactionDao.beneficiaryAccountActive(beneficiaryId);
	}

	public void beneficiaryAccountDeactive(int beneficiaryId) throws DataBaseException {
		transactionDao.beneficiaryAccountDeactive(beneficiaryId);
		
	}
}


