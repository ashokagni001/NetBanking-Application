package com.i2i.netbankingapplication.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.netbankingapplication.Constants;
import com.i2i.netbankingapplication.dao.CustomerTransactionDao;
import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.exception.TransactionCustomException;
import com.i2i.netbankingapplication.model.Account;
import com.i2i.netbankingapplication.model.Beneficiary;
import com.i2i.netbankingapplication.model.CustomerTransaction;
import com.i2i.netbankingapplication.model.User;
import com.i2i.netbankingapplication.service.BeneficiaryManager;
import com.i2i.netbankingapplication.service.BranchManager;
import com.i2i.netbankingapplication.service.CustomerTransactionManager;

/**
 * <p>
 *     When request comes from CustomerTransactionController. 
 *     CustomerTransactionManager performs add or fetch or fetchAll or update transaction with model(CustomerTransaction),
 *     dao(CustomerTransaction) and return the responses to CustomerTransactionController.
 *     CustomerTransactionManager operate passing value's to CustomerTransactionDao based on requset's from CustomerTransactionController.
 *     It handles the TransactionCustomException.
 *     It service validate the business logics using Constants class.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-29.
 *
 */
@Service("customerTransactionManager")
public class CustomerTransactionManagerImpl extends GenericManagerImpl<CustomerTransaction, Long> 
         implements CustomerTransactionManager {
     @Autowired
     private CustomerTransactionDao customerTransactionDao;
    
     @Autowired
     private BranchManager branchManager; 
     
     @Autowired
     private BeneficiaryManager beneficiaryManager;
     
     /**
      * <p> 
      *     Get the customer transaction detail from CustomerTransactionController.
      *     If exception occurs return error message to TransactionController.
      * </p>
      * 
      * @param user
      *    user holds the information of user.
      * @param creditAccountNumber
      *    creditAccountNumber of transaction used to add new transaction.
      * @param amount
      *    amount of transaction used to add new transaction.
      *    
      * @return message (success or failure).
      * 
      * @throws TransactionCustomException
      *     If there is an error in the customer yransaction attribute exception is handle by TransactionCustomException.
      * @throws DataBaseException
      *     If there is an error in getting the object like NullPointerException,
      *     NumberFormatException, HibernateException.
      */
    public String addTransaction(User user, String creditAccountNumber, 
            double amount) throws TransactionCustomException, DataBaseException {
        Account debitAccount = getAccountByAccountNumber(user.getAccountNumber());
        double currentAmount = debitAccount.getBalance();
        double balanceAmount = (currentAmount - amount);
        //check the balance amount available or not.
        if (balanceAmount > Constants.SIZE) {
            debitAccount.setBalance(balanceAmount);
            customerTransactionDao.insertTransaction(new CustomerTransaction(debitAccount, 
                    branchManager.getAccountByAccountNumber(creditAccountNumber), amount, Constants.STATUS_REQUEST), debitAccount);
            return "YOUR TRANSACTION DETAIL SEND OUR TRANSACTION APPROVER PLEASE WAIT";
        } else {
            throw new TransactionCustomException("YOUR CREDIT AMOUNT VALUE IS MUST BE LESSER THAN CURRENT AMOUNT :Rs " + currentAmount );
        }
    }
    
    /**
     * <p>
     *     If request comes TransactionController, It will calling to 
     *     getAccountByAccountNumber method in BranchManager.
     *     Return to the Account detail based on accountNumber.
     * </p>
     * 
     * @return to the Account detail based on accountNumber.
     *     
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.    
     */
    public Account getAccountByAccountNumber(String accountNumber) throws DataBaseException {
        return branchManager.getAccountByAccountNumber(accountNumber);
    }

    /**
     * <p>
     *     This Method call to getAllNotifications method in CustomerTransactionDao.
     *     and return to the list of notifications or custom exception message(failure).
     *     This method handle the TransactionCustomException and DataBaseException.
     *     TransactionCustomException means it is our business logic exception. 
     * </p>
     * 
     * @return list of transactions
     * 
     * @throws TransactionCustomException
     *     If there is an error in the customer yransaction attribute exception is handle by TransactionCustomException.
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    public List<CustomerTransaction> getAllNotifications() throws DataBaseException, TransactionCustomException {
        List<CustomerTransaction> transactions = new ArrayList<CustomerTransaction>();
        for (CustomerTransaction transaction : customerTransactionDao.retriveAllTransactions()) {
            if (transaction.getStatus().equals(Constants.STATUS_REQUEST)) {
                transactions.add(transaction);
            } 
        }
        //Check the notifications available or not.
        if (Constants.NOTIFICATION_SIZE != transactions.size()) {
            return transactions;
        } else {
            throw new TransactionCustomException("TRANSACTION NOTIFICATION NOT AVAILABLE");
        }
    }

    /**
     * <p>
     *     If request comes CustomerTransactionController, It will calling to retriveAllTransactions method in CustoemrTransactionDao.
     *     Return to the list of Customer Transactions.
     * </p>
     * 
     * @param user
     *     user holds the information of user.
     * @param customerAccountNumber
     *     customerAccountNumber of Customer used to check user transactions based on account number.
     *     
     * @return list of customer transactions.
     *     
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.    
     */
    public List<CustomerTransaction> getCustomerMiniStatements(User user) throws DataBaseException {
        List<CustomerTransaction> transactions = new ArrayList<CustomerTransaction>();
        String customerAccountNumber = user.getAccountNumber();
        for (CustomerTransaction transaction : customerTransactionDao.retriveAllTransactions()) {
            if (transaction.getDebitAccount().getAccountNumber().equals(customerAccountNumber)) {
                transactions.add(transaction);
            } else if (transaction.getCreditAccount().getAccountNumber().equals(customerAccountNumber)) {
                if (!(transaction.getStatus().equals(Constants.STATUS_REQUEST))) {
                    transactions.add(transaction);
                }
            }
        }
        return transactions;
    }
    
    /**
     * <p>
     *     If request comes TransactionController, It will calling to retriveAllTransactions method in TransactionDao.
     *     Return to the lists of Customer Transactions.
     * </p>
     * 
     * @return list of CustomerTransactions.
     *     
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    public List<CustomerTransaction> getAllTransactions() throws DataBaseException {
        return customerTransactionDao.retriveAllTransactions();
    }
    
    /**
     * <p>
     *     If request comes TransactionController, It will calling to 
     *     getAccountByAccountNumber method in BranchManager.
     *     Return to the Account detail based on accountNumber.
     * </p>
     * 
     * @return to the account detail based on accountNumber.
     *     
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.    
     */
    public Account getCustomerAccount(String accountNumber) throws DataBaseException {
        return branchManager.getAccountByAccountNumber(accountNumber);
    }
    
    /**
     * <p>
     *     If request comes TransactionController, It will calling to getAllBeneficiaries method in BeneficiaryManager.
     *     Return to the lists of Customer Beneficiaries.
     * </p>
     * 
     * @return list of beneficiaries.
     * 
     * @throws TransactionCustomException
     *     If there is an error in the customer yransaction attribute exception is handle by TransactionCustomException.
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    @SuppressWarnings("unchecked")
    public List getAllBeneficiaries() throws TransactionCustomException , DataBaseException {
        return beneficiaryManager.getAllBeneficiaries();
    }
    
    /**
     * <p>
     *     This method used to update the transaction status based on transactionId,
     *     accountNumber, amount, amount and user.If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     * </p>
     * 
     * @param transactionId
     *     transactionId of Transaction used to update the transaction status based on transaction id.
     * @param accountNumber
     *     accountNumber of Transaction used to update the transaction status based on transaction id.
     * @param amount
     *     amount of Transaction used to update the transaction status based on transaction id. 
     * @param action
     *      contains String of action used to update the transaction.
     * @param user
     *     user holds the information of user.
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     */
    public void updateTransactionStatus(int transactionId, String accountNumber, Double amount, String action,
            User user) throws DataBaseException {
        Account account = branchManager.getAccountByAccountNumber(accountNumber);
        account.setBalance(account.getBalance() + amount);
        CustomerTransaction customerTransaction = customerTransactionDao.retriveTransactionById(transactionId);
        customerTransaction.setStatus(action);
        customerTransaction.setUser(user);
        customerTransactionDao.updateTransactionStatus(account, customerTransaction);
    }
}
