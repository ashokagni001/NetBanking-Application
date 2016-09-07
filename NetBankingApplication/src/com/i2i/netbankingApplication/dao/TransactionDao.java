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
import com.i2i.netbankingApplication.model.Branch;
import com.i2i.netbankingApplication.model.CustomerTransaction;

public class TransactionDao {
	HibernateConnection hibernateConnectionObject  = HibernateConnection.getInstance();	
	Configuration configuration = hibernateConnectionObject.getConfiguration();
	SessionFactory sessionFactory = hibernateConnectionObject.getSessionFactory();
    
	public void addTransaction(CustomerTransaction customerTransaction, Account debitAccount,
			Account criditAccount) throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    Transaction transaction = null;
	    try {
	        transaction = session.beginTransaction();
	        session.save(customerTransaction);
	        customerTransaction.setDebitAccount(debitAccount);
	        customerTransaction.setCriditAccount(criditAccount);
	        session.update(customerTransaction);
	        session.update(debitAccount);
	        transaction.commit(); 
		} catch (HibernateException e) {
			throw new DataBaseException("PLEASE CHECK YOUR DATAS YOUR DATA IS NOT VALID.PLEASE TRY AGAIN.addTransaction" );  
	    } finally {
	        session.close(); 
	    }
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
}