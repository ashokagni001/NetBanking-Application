package com.i2i.netbankingapplication.service;


import java.util.List;

import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.exception.TransactionCustomException;
import com.i2i.netbankingapplication.model.Account;
import com.i2i.netbankingapplication.model.CustomerTransaction;
import com.i2i.netbankingapplication.model.User;

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
public interface CustomerTransactionManager extends GenericManager<CustomerTransaction, Long> {
    
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
     * @return status message (success or failure).
     * 
     * @throws TransactionCustomException
     *     If there is an error in the customer yransaction attribute exception is handle by TransactionCustomException.
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    String addTransaction(User user, String creditAccountNumber, double amount) 
            throws TransactionCustomException, DataBaseException;
    
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
    List getAllNotifications() throws TransactionCustomException, DataBaseException;
    
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
     *     NumberFormatException, HibernateException.
     */
    void updateTransactionStatus(int transactionId, String accountNumber, Double amount,String action, User user) throws DataBaseException;
    
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
     * @return list
     *     Return the list of customer transactions.
     *     
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.    
     */
    List getCustomerMiniStatements(User user) throws DataBaseException;
    
    /**
     * <p>
     *     If request comes TransactionController, It will calling to retriveAllTransactions method in customerTransactionDao.
     *     Return to the lists of notifications.
     * </p>
     * 
     * @return the list of Transactions.
     *     
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.    
     */
    List getAllTransactions() throws DataBaseException;
    
    /**
     * <p>
     *     If request comes CustomerTransactionController, It will calling to 
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
    Account getCustomerAccount(String accountNumber) throws DataBaseException;
    
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
    List<CustomerTransaction> getAllBeneficiaries() throws TransactionCustomException , DataBaseException;
}
