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
import com.i2i.netbankingapplication.model.CustomerTransaction;
import com.i2i.netbankingapplication.model.User;
import com.i2i.netbankingapplication.service.BranchManager;
import com.i2i.netbankingapplication.service.CustomerTransactionManager;

@Service("customerTransactionManager")
public class CustomerTransactionManagerImpl extends GenericManagerImpl < CustomerTransaction, Long >
    implements CustomerTransactionManager {
        @Autowired
        private CustomerTransactionDao customerTransactionDao;

        @Autowired
        private BranchManager branchManager;

        /**
         * <p> 
         *     Get the Transaction detail from TransactionController.
         *     It is passed to addTransaction method in customerTransactionDao and 
         *     If exception occurs return error message to TransactionController.
         * </p>
         * 
         * @param debitAccountNumber
         *     debitAccountNumber of Transaction to use add new Transaction.
         * @param creditAccountNumber
         *     creditAccountNumber of Transaction to use add new Transaction.
         * @param amount
         *     amount of Transaction to use add new Transaction.
         *      
         * @throws DataBaseException
         *     It handle all the custom exception in NetBanking Application.
         */
        public String addTransaction(User user, String creditAccountNumber,
            double amount) throws TransactionCustomException, DataBaseException {
            Account debitAccount = getAccountByAccountNumber(user.getAccountNumber());
            double currentAmount = debitAccount.getBalance();
            double balanceAmount = (currentAmount - amount);
            System.out.println(creditAccountNumber + "flow 1");
            //check the balance amount valid or not.
            if (balanceAmount > Constants.SIZE) {
                debitAccount.setBalance(balanceAmount);
                customerTransactionDao.insertTransaction(new CustomerTransaction(debitAccount,
                    branchManager.getAccountByAccountNumber(creditAccountNumber), amount, "Request"), debitAccount);
                return "YOUR TRANSACTION DETAIL SEND OUR TRANSACTION APPROVER PLEASE WAIT";
            } else {
                throw new TransactionCustomException("YOUR CREDIT AMOUNT VALUE IS MUST BE LESSER THAN CURRENT AMOUNT :Rs " + currentAmount);
            }
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
         * @return Account
         *     Return Account Detail.
         *     
         * @throws DataBaseException
         *     It handle all the custom exception in NetBanking Application.
         */
        public Account getAccountByAccountNumber(String accountNumber) throws DataBaseException {
            return branchManager.getAccountByAccountNumber(accountNumber);
        }

        /**
         * <p>
         *     If request comes TransactionController, It will calling to retriveAllTransactions method in customerTransactionDao.
         *     Return to the lists of notifications.
         * </p>
         * 
         * @return list
         *     Return the list of Transactions.
         *     
         * @throws DataBaseException
         *     It handle all the custom exception in NetBanking Application.
         */
        public List < CustomerTransaction > getAllNotifications() throws DataBaseException, TransactionCustomException {
            List < CustomerTransaction > transactions = new ArrayList < CustomerTransaction > ();
            for (CustomerTransaction transaction: customerTransactionDao.retriveAllTransactions()) {
                if (transaction.getStatus().equals("Request")) {
                    transactions.add(transaction);
                }
            }
            if (Constants.NOTIFICATION_SIZE != transactions.size()) {
                return transactions;
            } else {
                throw new TransactionCustomException("NO TRENSACTION PROCESS STILL NOW");
            }
        }

        /**
         * <p>
         *     If request comes TransactionController, get the transaction details and
         *     it will calling to transactionSuccess method in customerTransactionDao
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
        public void transactionSuccess(int transactionId, String creditAccountNumber, Double amount, User user)
        throws DataBaseException {
            Account creditAccount = customerTransactionDao.retrieveAccountByNumber(creditAccountNumber);
            double currentAmount = creditAccount.getBalance();
            double balanceAmount = (currentAmount + amount);
            creditAccount.setBalance(balanceAmount);
            customerTransactionDao.transactionSuccess(creditAccount, transactionId, user);
        }

        /**
         * <p>
         *     If request comes TransactionController, get the transaction details and
         *     It will calling to transactionFailure method in customerTransactionDao
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
        public void transactionFailure(int transactionId, String debitAccountNumber, Double amount, User user)
        throws DataBaseException {
            Account debitAccount = customerTransactionDao.retrieveAccountByNumber(debitAccountNumber);
            double currentAmount = debitAccount.getBalance();
            double balanceAmount = (currentAmount + amount);
            debitAccount.setBalance(balanceAmount);
            customerTransactionDao.transactionFailure(debitAccount, transactionId, user);
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
        public List < CustomerTransaction > getCustomerMiniStatements(User user) throws DataBaseException {
            List < CustomerTransaction > transactions = new ArrayList < CustomerTransaction > ();
            String customerAccountNumber = user.getAccountNumber();
            for (CustomerTransaction transaction: customerTransactionDao.retriveAllTransactions()) {
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
        public List getAllTransactions() throws DataBaseException {
            return customerTransactionDao.retriveAllTransactions();
        }

    }
