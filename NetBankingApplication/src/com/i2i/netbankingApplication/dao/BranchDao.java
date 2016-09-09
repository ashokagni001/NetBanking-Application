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


public class BranchDao {
HibernateConnection hibernateConnectionObject  = HibernateConnection.getInstance();	
	Configuration configuration = hibernateConnectionObject.getConfiguration();
	SessionFactory sessionFactory = hibernateConnectionObject.getSessionFactory();
	 
	public void addBranch(Branch branch) throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    Transaction transaction = null;
	    try {
	        transaction = session.beginTransaction();
		    session.save(branch); 
	        transaction.commit();                                                                    
		} catch (HibernateException e) {
			throw new DataBaseException("PLEASE CHECK YOUR DATAS " + branch + " YOUR DATA IS NOT VALID.PLEASE TRY AGAIN." );  
	    } finally {
	        session.close(); 
	    }
	}
	
	public void deleteBranchById(String IFSCode) throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    Transaction transaction = null;
	    try {
	        transaction = session.beginTransaction();
	        Branch branch = (Branch)session.get(Branch.class, IFSCode); 
	        session.delete(branch); 
	        transaction.commit();
	    } catch (HibernateException e) {
	    	throw new DataBaseException("CHECK IFSC " + IFSCode + "PLEASE INSERT VALID IFSC...\n");  
	    } finally {
	        session.close(); 
	    }
    }
	 
	public Branch retrieveBranchById(String IFSCode) throws DataBaseException {
	    Branch branch = null ;
	    Session session = sessionFactory.openSession();
	    Transaction transaction = null;
	    try {
	        transaction = session.beginTransaction();
	        branch = (Branch)session.get(Branch.class, IFSCode); 
	        transaction.commit();
	    } catch (HibernateException e) {
	    	throw new DataBaseException("CHECK IFSC " + IFSCode + "PLEASE INSERT VALID IFSC...\n");
	    } finally {
	        session.close(); 
	    } 
	    return branch; 
	}
	
	public List<Branch> retriveAllBranch() throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    try {
	        return session.createQuery("FROM Branch").list();
	    } catch (HibernateException e) {
	        throw new DataBaseException("DATA IS NOT AVAILABLE.INSERT DATA.");
	    } finally {
	        session.close();
	    }
	}
	 
	public void addAddress(String IFSCode, Address address) throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    Transaction transaction = null;
	    try {
	        transaction = session.beginTransaction();
	        Branch branch = (Branch)session.get(Branch.class, IFSCode);
		    session.save(address);
		    branch.setAddress(address);
	        session.update(branch);
	        transaction.commit();                                                                    
		} catch (HibernateException e) {
			throw new DataBaseException("DATA IS NOT AVAILABLE.INSERT DATA.");
	    } finally {
	        session.close(); 
	    }
	}
	 
	public List<Address> retriveAllAddresses() throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    try {
	        return session.createQuery("from Address").list();
	    } catch(HibernateException e) {
	        throw new DataBaseException("DATA IS NOT AVAILABLE.INSERT DATA.");
	    } finally {
	        session.close();
	    }
	}

	public Address retrieveAddressById(int addressId) throws DataBaseException {
		Address address;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
		    address = (Address)session.get(Address.class, addressId);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DataBaseException("Oops Some Problem occured.. please try again later");
		} finally {
			session.close();
		}
		return address;
	}
	
	public void addAccount(Account account) throws DataBaseException {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
		    session.save(account); 
	        transaction.commit();                                                                    
		} catch (HibernateException e) {
			throw new DataBaseException("PLEASE CHECK YOUR DATAS " + account + " YOUR DATA IS NOT VALID.PLEASE TRY AGAIN." );  
	    } finally {
	        session.close(); 
		}
	}
	
	public List<Account> retriveAllAccount() throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    try {
	        return session.createQuery("from Account").list();
	    } catch(HibernateException e) {
	        throw new DataBaseException("DATA IS NOT AVAILABLE.INSERT DATA.");
	    } finally {
	        session.close();
	    }
	}
}
