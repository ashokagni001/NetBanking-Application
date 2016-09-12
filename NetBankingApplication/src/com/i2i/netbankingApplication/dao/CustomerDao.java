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

/**
 * <p>
 *     When request comes from CustomerService. CustomerDao performs add or delete or fetch or fetchAll 
 *     with database and return the responses to CustomerService.
 *     It handles the HibernateException and DataBaseException.
 *     Connect to the HibernateConnection class.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-03
 */

public class CustomerDao {
	HibernateConnection hibernateConnectionObject  = HibernateConnection.getInstance();	
	Configuration configuration = hibernateConnectionObject.getConfiguration();
	SessionFactory sessionFactory = hibernateConnectionObject.getSessionFactory();
    
	/**
	 * <p>
	 *     Get the customer object from CustomerService and add Customer to database. 
	 *     Get the accountNumber from CustomerService and add customer id in Account to database.
	 * </p>
	 * 
	 * @param accountNumber
	 *     accountNumber of customer
	 *     
	 * @param customer
	 *     Object of Customer model class.It object used for add Customer.
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException and HibernateException.
	 */
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
	
	/**
     * <p>
     *     Get the customer Id from CustomerService.
     *     Retrieves customer data from database and returns customer object to CustomerService.
     * </p>
     * 
     * @param customerId
     *      Id of Customer to view.
     * 
     * @return customer
     *      Object of Customer class.
     * 
     * @throws DataBaseException
     *      If there is an error in the given data like BadElementException and HibernateException.
	 */
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
	
	/**
	 * <p>
     *     Retrieves all customers from database.
     *     Return all customers in List type.
     * </p>
     * 
     * @return List
     *     Return list of customers. 
     *         
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException and HibernateException.
	 */
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
	
	/**
	 * <p>
	 *     Get the address object from CustomerService and add Customer Address to database. 
	 *     Get the customerId from CustomerService and update address in Customer to database.
	 * </p>
	 * 
	 * @param customerId
	 *     customerId of Customer to add address.
	 *     
	 * @param address
	 *     Object of Address class.
	 *     
	 * @return message
	 *     return status message(Success and customer Id and passWord or failure)
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException and HibernateException.
	 */
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
	
	/**
	 * <p>
     *     Retrieves all address from database.
     *     Return all address in List type.
     * </p>
     * 
	 * @return list
	 *     Return list of address.
	 *      
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException and HibernateException.
	 */
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
	
	/**
	 * <p>
	 *     Get the address Id from CustomerService.
	 *     Retrieves customer address from database and returns Address object to CustomerService.
	 * </p>
	 * 
	 * @param addressId
	 *     addressId of Address to view.
	 *      
	 * @return Address
	 *     Object of Address class.
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException and HibernateException.
	 */
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
	
	/**
	 * <p>
	 *     Get the accountNumber from CustomerService.
	 *     Retrieves Account from database and returns Account object to CustomerService.
	 * </p>
	 * 
	 * @param accountNumber
	 *     accountNumber of Customer.
	 *     
	 * @return Account
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
	    	throw new DataBaseException("Oops Some Problem occured.. please try again later");
	    } finally {
	        session.close(); 
	    } 
	}
	
	/**
	 *  Get the UserRole object from CustomerService and add UserRole to database. 
	 * 
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException and HibernateException.
	 */
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
    
	/**
	 * <p>
     *     Retrieves all Role from database.
     *     Return all Role in List type.
     * </p>
     * 
	 * @return Role
	 *     Return the list of roles.
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException and HibernateException.
	 */
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
    
	/**
	 * <p>
	 *     Get the id from CustomerService.
	 *     Retrieves Role from database and returns Role object to CustomerService.
	 * </p>
	 * 
	 * @param id
	 *     id of Role to view.
	 * 
	 * @return Role
	 *     Object of Role class.
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException and HibernateException.
	 */
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
    
	/**
	 * <p>
     *     Retrieves all UserRole from database.
     *     Return all UserRole in List type.
     * </p>
     * 
	 * @return UserRole
	 *     Object of UserRole class.
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException and HibernateException.
	 */
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
