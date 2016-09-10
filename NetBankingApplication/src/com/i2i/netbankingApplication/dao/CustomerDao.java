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
import com.i2i.netbankingApplication.model.Role;
import com.i2i.netbankingApplication.model.UserRole;

public class CustomerDao {
	HibernateConnection hibernateConnectionObject  = HibernateConnection.getInstance();	
	Configuration configuration = hibernateConnectionObject.getConfiguration();
	SessionFactory sessionFactory = hibernateConnectionObject.getSessionFactory();

	public void insertUser(String accountNumber, Customer customer) throws DataBaseException {
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
        	throw new DataBaseException("CHECK YOUR PLEASE INSERT VALID CUSTOMER DETAIL..");
        } finally {
            session.close();
        }
    }
	
	public Customer retrieveCustomerById(String customerId) throws DataBaseException {
	    Customer customer = null ;
	    Session session = sessionFactory.openSession();
	    Transaction transaction = null;
	    try {
	        transaction = session.beginTransaction();
	        customer = (Customer)session.get(Customer.class, customerId); 
	        transaction.commit();
	    } catch (HibernateException e) {
	    	throw new DataBaseException("CHECK YOUR " + customerId + "PLEASE INSERT VALID CUSTOMER ID..");
	    } finally {
	        session.close(); 
	    } 
	    return customer; 
	}
	
	public List<Customer> retriveAllCustomer() throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    try {
	        return session.createQuery("FROM Customer").list();
	    } catch (HibernateException e) {
	        throw new DataBaseException("DATA IS NOT AVAILABLE.INSERT DATA.");
	    } finally {
	        session.close();
	    }
	}
	
	public String addAddress(String customerId, Address address) throws DataBaseException {
	    Session session = sessionFactory.openSession();
	    Transaction transaction = null;
	    try {
	        transaction = session.beginTransaction();
	        Customer customer = (Customer)session.get(Customer.class, customerId);
	        session.save(address); 
		    customer.setAddress(address);
	        session.update(customer);
	        transaction.commit();  
	        return ("Customer register successfully ::  customer ID :" + customer.getCustomerId() +" PASSWORD :" + customer.getPassWord());
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
	
	public void insertRole(UserRole userRole) throws DataBaseException {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(userRole);
			transaction.commit();
		} catch (HibernateException exp) {
			throw new DataBaseException("Sorry information can't save please try again" + exp);
		} finally {
			session.close();
		}
	}

	public List<Role> retriveAllRole() throws DataBaseException {
		Session session = sessionFactory.openSession();
		try {
			return session.createQuery("FROM Role").list();
		} catch (HibernateException exp) {
			throw new DataBaseException("DATA IS NOT AVAILABLE.INSERT DATA." + exp);
		} finally {
			session.close();
		}
	}

	public Role retrieveRoleById(String id) throws DataBaseException {
		Session session = sessionFactory.openSession();
		try {
			return (Role) session.get(Role.class, id);
		} catch (HibernateException e) {
			throw new DataBaseException("CHECK ID " + id + "PLEASE INSERT VALID ID...\n");
		} finally {
			session.close();
		}
	}

	public List<UserRole> retriveAllUserRole() throws DataBaseException {
		Session session = sessionFactory.openSession();
		try {
			return session.createQuery("FROM UserRole").list();
		} catch (HibernateException e) {
			throw new DataBaseException("Data is not available please insert the data." + e);
		} finally {
			session.close();
		}
	}
}
