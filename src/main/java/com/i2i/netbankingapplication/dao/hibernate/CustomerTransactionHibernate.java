package com.i2i.netbankingapplication.dao.hibernate;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.i2i.netbankingapplication.dao.CustomerTransactionDao;
import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.model.Account;
import com.i2i.netbankingapplication.model.CustomerTransaction;
import com.i2i.netbankingapplication.model.User;

/**
 * <p>
 *     When request comes from CustomerTransactionManager. TransactionDao performs add or delete or fetch or fetchAll 
 *     with database and return the responses to TransactionService.
 *     It handles the HibernateException and DataBaseException.
 *     Connect to the HibernateConnection class use to create hibernate connection.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-27
 */
@Repository("customerTransactionDao")
@Transactional
public class CustomerTransactionHibernate extends GenericDaoHibernate < CustomerTransaction, Long > implements CustomerTransactionDao {

    /**
     * Constructor to create a Generics-based version using Branch as the entity
     */
    public CustomerTransactionHibernate() {
        super(CustomerTransaction.class);
    }

    /**
     * <p>
     *     Get the Transaction object from CustomerTransactionManager and add CustomerTransaction to database. 
     * </p>
     * 
     * @param customerTransaction
     *     object of CustomerTransaction to add.
     * @param debitAccount
     *     debitAccount of Account to Add transaction
     *     
     * @return message
     *     return status message(Success or failure)
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application and HibernateException.
     */
    public void insertTransaction(CustomerTransaction customerTransaction, Account debitAccount) throws DataBaseException {
        try {
            Session session = getSession();
            session.save(customerTransaction);
            session.update(debitAccount);
        } catch (HibernateException e) {
            throw new DataBaseException("PLEASE CHECK YOUR DATAS YOUR DATA IS NOT VALID.PLEASE TRY AGAIN.addTransaction");
        }
    }

    /**
     * <p>
     *     Get the accountNumber from CustomerTransactionManager.
     *     Retrieves customer Account from database and returns account object to CustomerTransactionManager.
     * </p>
     * 
     * @param accountNumber
     *     accountNumber of Customer Account to use retrieveAccount detail.
     *      
     * @return account
     *     Return account detail.
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application and HibernateException.
     */
    public Account retrieveAccountByNumber(String accountNumber) throws DataBaseException {
        try {
            return (Account) getSession().get(Account.class, accountNumber);
        } catch (HibernateException e) {
            throw new DataBaseException("OOPS SOME PROBLEM OCCURED.. PLEASE TRY AGAIN LATER");
        }
    }

    /**
     * <p>
     *     Retrieves all CustomerTransactions from database.
     *     Return all CustomerTransactions in List type.
     * </p>
     * 
     * @return list
     *     return the list of Customer Transactions.
     * 
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application and HibernateException.
     */
    public List < CustomerTransaction > retriveAllTransactions() throws DataBaseException {
        try {
            return getSession().createQuery("FROM CustomerTransaction").list();
        } catch (HibernateException e) {
            throw new DataBaseException("TRANSACTIONS IS NOT AVAILABLE..");
        }
    }

    /**
     * <p>
     *     Update the balanceAmount to Credit Account using database.
     * </p>
     * @param accountNumber
     *     accountNumber of Customer.
     * @param balanceAmount
     *     balanceAmount of customer Account.
     * @param transactionId
     *     transactionId of Transaction.
     *      
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application and HibernateException.
     */
    public void transactionSuccess(Account criditAccount, int transactionId, User user)
    throws DataBaseException {
        CustomerTransaction customerTransaction = null;
        try {
            Session session = getSession();
            session.update(criditAccount);
            customerTransaction = (CustomerTransaction) session.get(CustomerTransaction.class, transactionId);
            customerTransaction.setUser(user);
            customerTransaction.setStatus("Success");
            session.update(customerTransaction);
        } catch (HibernateException e) {
            throw new DataBaseException("OOPS SOME PROBLEM OCCURED.. PLEASE TRY AGAIN LATER");
        }
    }

    /**
     * <p>
     *     Update the balanceAmount to Debit Account using database.
     * </p>
     *  
     * @param accountNumber
     *     accountNumber of Customer.
     * @param balanceAmount
     *     balanceAmount of customer Account.
     * @param transactionId
     *     transactionId of Transaction.
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application and HibernateException.
     */
    public void transactionFailure(Account debitAccount, int transactionId, User user)
    throws DataBaseException {
        CustomerTransaction customerTransaction = null;
        try {
            Session session = getSession();
            session.update(debitAccount);
            customerTransaction = (CustomerTransaction) session.get(CustomerTransaction.class, transactionId);
            customerTransaction.setUser(user);
            customerTransaction.setStatus("Failure");
            session.update(customerTransaction);
        } catch (HibernateException e) {
            throw new DataBaseException("OOPS SOME PROBLEM OCCURED.. PLEASE TRY AGAIN LATER");
        }
    }
}
