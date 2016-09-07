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

public class CustomerDao {
	HibernateConnection hibernateConnectionObject  = HibernateConnection.getInstance();	
	Configuration configuration = hibernateConnectionObject.getConfiguration();
	SessionFactory sessionFactory = hibernateConnectionObject.getSessionFactory();

	public void insertUser(String accountNumber, Customer customer) {
		Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Account account = (Account)session.get(Account.class, accountNumber);
            session.save(customer);
            account.setCustomer(customer);
            session.update(account);
            transaction.commit();
        } catch(Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
    }
	
	public Customer retrieveCustomerById(String customerId) throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    try {
	        return (Customer)session.get(Customer.class, customerId); 
	    } catch (HibernateException e) {
	    	throw new DataBaseException("CHECK YOUR " + customerId + "PLEASE INSERT VALID CUSTOMER ID..");
	    } finally {
	        session.close(); 
	    } 
	}
	
	public List<Customer> retriveAllCustomer() throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    try {
	        return session.createQuery("FROM Customer").list();
	    } catch (HibernateException e) {
	    	System.out.println(e);
	        throw new DataBaseException("retriveAllCustomer DATA IS NOT AVAILABLE.INSERT DATA.");
	    } finally {
	        session.close();
	    }
	}
	
	public void addAddress(String customerId, Address address) throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    Transaction transaction = null;
	    try {
	        transaction = session.beginTransaction();
	        Customer customer = (Customer)session.get(Customer.class, customerId);
	        session.save(address); 
		    customer.setAddress(address);
	        session.update(customer);
	        transaction.commit();   
		} catch (HibernateException e) {
			System.out.println(e);
			throw new DataBaseException("addAddress DATA IS NOT AVAILABLE.INSERT DATA.");
	    } finally {
	        session.close(); 
	    }
	}
	 
	public List<Address> retriveAllAddresses() throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    try {
	        return session.createQuery("from Address").list();
	    } catch(HibernateException e) {
	    	System.out.println(e);
	        throw new DataBaseException("retriveAllAddresses DATA IS NOT AVAILABLE.INSERT DATA.");
	    } finally {
	        session.close();
	    }
	}
	
	public Address retrieveAddressById(int addressId) throws DataBaseException {
		Session session = sessionFactory.openSession();
		try {
			return (Address)session.get(Address.class, addressId);
		} catch (HibernateException e) {
			throw new DataBaseException("Oops Some Problem occured.. please try again later");
		} finally {
			session.close();
		}
	}
	
	public Account retrieveAccountByNumber(String accountNumber) throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    try {
	        return (Account)session.get(Account.class, accountNumber); 
	    } catch (HibernateException e) {
	    	throw new DataBaseException("Oops Some Problem occured.. please try again later");
	    } finally {
	        session.close(); 
	    } 
	}
}
