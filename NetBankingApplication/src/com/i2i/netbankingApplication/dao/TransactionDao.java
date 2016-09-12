package com.i2i.netbankingApplication.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.hibernateConnection.HibernateConnection;
import com.i2i.netbankingApplication.model.Account;
import com.i2i.netbankingApplication.model.CustomerTransaction;

/**
 * <p>
 *     When request comes from TransactionService. TransactionDao performs add or delete or fetch or fetchAll 
 *     with database and return the responses to TransactionService.
 *     It handles the HibernateException and DataBaseException.
 *     Connect to the HibernateConnection class.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-03
 */
public class TransactionDao {
	HibernateConnection hibernateConnectionObject  = HibernateConnection.getInstance();	
	Configuration configuration = hibernateConnectionObject.getConfiguration();
	SessionFactory sessionFactory = hibernateConnectionObject.getSessionFactory();
    
	/**
	 * <p>
	 *     Get the Transaction object from TransactionService and add Transaction to database. 
	 * </p>
	 * 
	 * @param customerTransaction
	 *     object of CustomerTransaction to add.
	 *     
	 * @param debitAccount
	 *     debitAccount of Account to Add transaction
	 *     
	 * @return message
	 *     return status message(Success or failure)
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException and HibernateException.
	 */
	public String addTransaction(CustomerTransaction customerTransaction, Account debitAccount) throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    Transaction transaction = null;
	    try {
	    	transaction = session.beginTransaction();
	        session.save(customerTransaction);
	        session.update(debitAccount);
	        transaction.commit(); 
	        return ("Your transaction detail send our Transaction Approver please wait");
		} catch (HibernateException e) {
			throw new DataBaseException("PLEASE CHECK YOUR DATAS YOUR DATA IS NOT VALID.PLEASE TRY AGAIN.addTransaction" );  
	    } finally {
	        session.close(); 
	    }
	}
	
	/**
	 * <p>
     *     Get the transactionId from TransactionService.
     *     Retrieves transaction data from database and returns transaction object to TransactionService.
     * </p>
     * 
	 * @param transactionId
	 *    transactionId of Transaction to view.
	 *    
	 * @return CustomerTransaction
	 *     object of CustomerTransaction.
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException and HibernateException.
	 */
	public CustomerTransaction retrieveCustomerTransactionById(String transactionId) throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    try {
	       return(CustomerTransaction)session.get(CustomerTransaction.class, transactionId); 
	    } catch (HibernateException e) {
	    	throw new DataBaseException("CHECK YOUR " + transactionId + "PLEASE INSERT VALID CUSTOMER ID..");
	    } finally {
	        session.close(); 
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
	 *     If there is an error in the given data like BadElementException and HibernateException.
	 */
	public List<CustomerTransaction> retriveAllTransactions() throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    try {
	        return session.createQuery("FROM CustomerTransaction").list();
	    } catch (HibernateException e) {
	        throw new DataBaseException("DATA IS NOT AVAILABLE.INSERT DATA.");
	    } finally {
	        session.close();
	    }
	}
	
	/**
     * <p>
     *     Retrieves all Account from database.
     *     Return all Accounts in List type.
     * </p>
     * 
	 * @return list
	 *     return the list of Accounts.
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException and HibernateException.
	 */
	public List<Account> retriveAllAccounts() throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    try {
	        return session.createQuery("FROM Account").list();
	    } catch (HibernateException e) {
	        throw new DataBaseException("DATA IS NOT AVAILABLE.INSERT DATA.");
	    } finally {
	        session.close();
	    }
	}
	
	/**
	 * <p>
	 *     Get the accountNumber from CustomerService.
	 *     Retrieves customer Account from database and returns account object to TransactionService.
	 * </p>
	 * 
	 * @param accountNumber
	 *      accountNumber of Customer Account.
	 *      
	 * @return account
	 *     Object of Account class.
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException and HibernateException.
	 */
	public Account retrieveAccountByNumber(String accountNumber) throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    try {
	        return (Account)session.get(Account.class, accountNumber); 
	    } catch (HibernateException e) {
	    	throw new DataBaseException("retrieveAccountByNumber dao");
	    } finally {
	        session.close(); 
	    } 
	}
    
	/**
	 * Update the balanceAmount to Cridit Account using database.
	 * 
	 * @param accountNumber
	 *     accountNumber of Customer.
	 *     
	 * @param balanceAmount
	 *      balanceAmount of customer Account.
	 *      
	 * @param transactionId
	 *      transactionId of Transaction.
	 *      
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException and HibernateException.
	 */
	public void transactionSuccess(String accountNumber, double balanceAmount, int transactionId) throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    Transaction transaction = null;
	    Account account = null;
	    CustomerTransaction customerTransaction = null;
	    try {
	        transaction = session.beginTransaction();
	        account = (Account)session.get(Account.class, accountNumber);
	        account.setBalance(balanceAmount);
	        session.update(account);
	        customerTransaction = (CustomerTransaction)session.get(CustomerTransaction.class, transactionId);
	        customerTransaction.setStatus("Success");
	        session.update(customerTransaction);
	        transaction.commit(); 
		} catch (HibernateException e) {
			throw new DataBaseException("Oops Some Problem occured.. please try again later" );  
	    } finally {
	        session.close(); 
	    }
	}
	
	/**
	 * Update the balanceAmount to Debit Account using database.
	 *  
	 * @param accountNumber
	 *     accountNumber of Customer.
	 *     
	 * @param balanceAmount
	 *     balanceAmount of customer Account.
	 *     
	 * @param transactionId
	 *     transactionId of Transaction.
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException and HibernateException.
	 */
	public void transactionFailure(String accountNumber, double balanceAmount, int transactionId) throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    Transaction transaction = null;
	    Account account = null;
	    CustomerTransaction customerTransaction = null;
	    try {
	        transaction = session.beginTransaction();
	        account = (Account)session.get(Account.class, accountNumber);
	        account.setBalance(balanceAmount);
	        session.update(account);
	        customerTransaction = (CustomerTransaction)session.get(CustomerTransaction.class, transactionId);
	        customerTransaction.setStatus("Failure");
	        session.update(customerTransaction);
	        transaction.commit(); 
		} catch (HibernateException e) {
			throw new DataBaseException("Oops Some Problem occured.. please try again later" );  
	    } finally {
	        session.close(); 
	    }
	}
	
	/**
	 * <p>
     *     Retrieves all CustomerTransactions in particular date from database.
     *     Return all CustomerTransactions in List type.
     * </p>
     * 
	 * @return list
	 *     return the list of CustomerTransactions.
	 *     
	 * @param formDate
	 *     fromDate of transaction Date.
	 *     
	 * @param toDate
	 *     toDate of transaction Date.
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException and HibernateException.
	 */
	public List<CustomerTransaction> retriveTransactionByDate(String formDate, String toDate) throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    try {
	        return session.createQuery("FROM CustomerTransaction WHERE date BETWEEN '"+formDate +"' AND '" +toDate +"'").list();
	    } catch (HibernateException e) {
	        throw new DataBaseException("DATA IS NOT AVAILABLE.INSERT DATA.");
	    } finally {
	        session.close();
	    }
	}
}