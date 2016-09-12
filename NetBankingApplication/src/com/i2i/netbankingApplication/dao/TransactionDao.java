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
import com.i2i.netbankingApplication.model.Address;
import com.i2i.netbankingApplication.model.Branch;
import com.i2i.netbankingApplication.model.Customer;
import com.i2i.netbankingApplication.model.CustomerTransaction;

public class TransactionDao {
	HibernateConnection hibernateConnectionObject  = HibernateConnection.getInstance();	
	Configuration configuration = hibernateConnectionObject.getConfiguration();
	SessionFactory sessionFactory = hibernateConnectionObject.getSessionFactory();
    
	public String addTransaction(CustomerTransaction customerTransaction, Account debitAccount) throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    Transaction transaction = null;
	    try {
	        transaction = session.beginTransaction();
	        session.save(customerTransaction);
	        session.update(debitAccount);
	        transaction.commit();
            return ("YOUR TRANSACTION SENDING REQUSET SUCCESSFULL");
		} catch (HibernateException e) {
			throw new DataBaseException("PLEASE CHECK YOUR DATAS YOUR DATA IS NOT VALID.PLEASE TRY AGAIN.addTransaction"+e);  
	    } finally {
	        session.close(); 
	    }
	}
	
	public CustomerTransaction retrieveCustomerTransactionById(String transactionId) throws DataBaseException {
		CustomerTransaction customerTransaction = null ;
	    Session session = sessionFactory.openSession();
	    Transaction transaction = null;
	    try {
	        transaction = session.beginTransaction();
	        customerTransaction = (CustomerTransaction)session.get(CustomerTransaction.class, transactionId); 
	        transaction.commit();
	    } catch (HibernateException e) {
	    	throw new DataBaseException("CHECK YOUR " + transactionId + "PLEASE INSERT VALID CUSTOMER ID..");
	    } finally {
	        session.close(); 
	    } 
	    return customerTransaction; 
	}
	
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
	
	public Account retrieveAccountByNumber(String accountNumber) throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    try {
	        return (Account)session.get(Account.class, accountNumber); 
	    } catch (HibernateException e) {
	    	throw new DataBaseException("retrieveAccountByNumber dao" + e);
	    } finally {
	        session.close(); 
	    } 
	}

	public Account retrieveAccountDetail(String accountNumber) throws DataBaseException {
		Account account;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
		    account = (Account)session.get(Account.class, accountNumber);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DataBaseException("Oops Some Problem occured.. please try again later");
		} finally {
			session.close();
		}
		return account;
	}
	
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