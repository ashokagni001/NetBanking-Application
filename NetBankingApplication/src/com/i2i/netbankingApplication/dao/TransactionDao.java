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
import com.i2i.netbankingApplication.model.Beneficiary;
import com.i2i.netbankingApplication.model.Customer;
import com.i2i.netbankingApplication.model.CustomerTransaction;
import com.i2i.netbankingApplication.model.UserRole;

/**
 * <p>
 *     When request comes from TransactionService. TransactionDao performs add or delete or fetch or fetchAll 
 *     with database and return the responses to TransactionService.
 *     It handles the HibernateException and DataBaseException.
 *     Connect to the HibernateConnection class use to create hibernate connection.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-03
 */
public class TransactionDao {
	
   /**
	* <p>
	*     Connect to the HibernateConnection class use to create hibernate connection.
	*     and create SessionFactory.
	* </p>
	* 
	* @return Session
	*     Return the new session object. 
	*     
	* @throws DataBaseException
	*     It handle all the custom exception in NetBanking Application and HibernateException.
	*/
	private Session hibernateConncetion() throws DataBaseException {
		try {
		    HibernateConnection hibernateConnectionObject  = HibernateConnection.getInstance();	
		    Configuration configuration = hibernateConnectionObject.getConfiguration();
		    SessionFactory sessionFactory = hibernateConnectionObject.getSessionFactory();
		    return sessionFactory.openSession();
		} catch (DataBaseException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
    
	/**
	 * <p>
	 *     Get the Transaction object from TransactionService and add Transaction to database. 
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
	public void insertTransaction(CustomerTransaction customerTransaction, Account debitAccount) throws DataBaseException, DataBaseException {
	    Session session = hibernateConncetion();
	    Transaction transaction = null;
	    try {
	    	transaction = session.beginTransaction();
	        session.save(customerTransaction);
	        session.update(debitAccount);
	        transaction.commit(); 
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
	 *     It handle all the custom exception in NetBanking Application and HibernateException.
	 */
	public CustomerTransaction retrieveCustomerTransactionById(String transactionId) throws DataBaseException {
	    Session session = hibernateConncetion();
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
	 *     It handle all the custom exception in NetBanking Application and HibernateException.
	 */
	public List<CustomerTransaction> retriveAllTransactions() throws DataBaseException {
	    Session session = hibernateConncetion();
	    try {
	        return session.createQuery("FROM CustomerTransaction").list();
	    } catch (HibernateException e) {
	        throw new DataBaseException("TRANSACTIONS IS NOT AVAILABLE..");
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
	 *     It handle all the custom exception in NetBanking Application and HibernateException.
	 */
	public List<Account> retriveAllAccounts() throws DataBaseException {
	    Session session = hibernateConncetion();
	    try {
	        return session.createQuery("FROM Account").list();
	    } catch (HibernateException e) {
	        throw new DataBaseException("ACCOUNTS IS NOT AVAILABLE.");
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
	 *     accountNumber of Customer Account.
	 *      
	 * @return account
	 *     Object of Account class.
	 *     
	 * @throws DataBaseException
	 *     It handle all the custom exception in NetBanking Application and HibernateException.
	 */
	public Account retrieveAccountByNumber(String accountNumber) throws DataBaseException {
	    Session session = hibernateConncetion();
	    try {
	        return (Account)session.get(Account.class, accountNumber); 
	    } catch (HibernateException e) {
	    	throw new DataBaseException("OOPS SOME PROBLEM OCCURED.. PLEASE TRY AGAIN LATER");
	    } finally {
	        session.close(); 
	    } 
	}
    
	/**
	 * Update the balanceAmount to Credit Account using database.
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
	public void transactionSuccess(Account criditAccount, int transactionId, Customer approver)
			throws DataBaseException {
		Session session = hibernateConncetion();
		Transaction transaction = null;
		CustomerTransaction customerTransaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(criditAccount);
			customerTransaction = (CustomerTransaction) session.get(CustomerTransaction.class, transactionId);
			customerTransaction.setCustomer(approver);
			customerTransaction.setStatus("Success");
			session.update(customerTransaction);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DataBaseException("OOPS SOME PROBLEM OCCURED.. PLEASE TRY AGAIN LATER");
		} finally {
			session.close();
		}
	}
	
	/**
	 * Update the balanceAmount to Debit Account using database.
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
	public void transactionFailure(Account debitAccount, int transactionId, Customer approver)
			throws DataBaseException {
		Session session = hibernateConncetion();
		Transaction transaction = null;
		Account account = null;
		CustomerTransaction customerTransaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(debitAccount);
			customerTransaction = (CustomerTransaction) session.get(CustomerTransaction.class, transactionId);
			customerTransaction.setCustomer(approver);
			customerTransaction.setStatus("Failure");
			session.update(customerTransaction);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DataBaseException("OOPS SOME PROBLEM OCCURED.. PLEASE TRY AGAIN LATER");
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
	 * @param formDate
	 *     fromDate of transaction Date.
	 * @param toDate
	 *     toDate of transaction Date.
	 *     
	 * @return list
	 *     return the list of CustomerTransactions.
     *
	 * @throws DataBaseException
	 *     It handle all the custom exception in NetBanking Application and HibernateException.
	 */
	public List<CustomerTransaction> retriveTransactionByDate(String formDate, String toDate) throws DataBaseException {
	    Session session = hibernateConncetion();
	    try {
	        return session.createQuery("FROM CustomerTransaction WHERE date BETWEEN '"+formDate +"' AND '" +toDate +"'").list();
	    } catch (HibernateException e) {
	        throw new DataBaseException("DATA IS NOT AVAILABLE.INSERT DATA.");
	    } finally {
	        session.close();
	    }
	}
    
	/**
	 * Get the Beneficiary detail from CustomerService and add Beneficiary detail to database. 
	 * 
	 * @throws DataBaseException
	 *     It handle all the custom exception in NetBanking Application..
	 */
	public void insertBeneficiaryAccount(Beneficiary beneficiary) throws DataBaseException {
		Session session = hibernateConncetion();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			System.out.println("flow3");
			session.save(beneficiary);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DataBaseException("SORRY INFORMATION CAN'T SAVE PLEASE TRY AGAIN" + e);
		} finally {
			session.close();
		}
	}
	
	public List<Beneficiary> retriveAllBeneficiaries() throws DataBaseException {
	    Session session = hibernateConncetion();
	    try {
	        return session.createQuery("FROM Beneficiary").list();
	    } catch (HibernateException e) {
	        throw new DataBaseException("BENEFICIARY ACCOUNTS IS NOT AVAILABLE.");
	    } finally {
	        session.close();
	    }
	}
}
