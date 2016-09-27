package com.i2i.netBankingApplication.dao.hibernate;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.i2i.netBankingApplication.dao.CustomerTransactionDao;
import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.model.Account;
import com.i2i.netBankingApplication.model.CustomerTransaction;

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
public class CustomerTransactionHibernate extends GenericDaoHibernate<CustomerTransaction, Long> implements CustomerTransactionDao {
	
	/**
     * Constructor to create a Generics-based version using Branch as the entity
     */
    public CustomerTransactionHibernate() {
        super(CustomerTransaction.class);
    }

    public void insertTransaction(CustomerTransaction customerTransaction, Account debitAccount) throws DataBaseException{
	    try {
	    	Session session = getSession();
	    	session.save(customerTransaction);
	    	session.update(debitAccount);
		} catch (HibernateException e) {
			throw new DataBaseException("PLEASE CHECK YOUR DATAS YOUR DATA IS NOT VALID.PLEASE TRY AGAIN.addTransaction" );  
	    }
    }

    /**
	 * <p>
	 *     Get the accountNumber from CustomerService.
	 *     Retrieves customer Account from database and returns account object to TransactionService.
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
	        return (Account)getSession().get(Account.class, accountNumber); 
	    } catch (HibernateException e) {
	    	throw new DataBaseException("OOPS SOME PROBLEM OCCURED.. PLEASE TRY AGAIN LATER");
	    }
	}
	
}
