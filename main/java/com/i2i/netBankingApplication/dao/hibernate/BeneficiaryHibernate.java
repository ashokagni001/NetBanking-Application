package com.i2i.netBankingApplication.dao.hibernate;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.i2i.netBankingApplication.dao.BranchDao;
import com.i2i.netBankingApplication.dao.BeneficiaryDao;
import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.model.Account;
import com.i2i.netBankingApplication.model.Beneficiary;
import com.i2i.netBankingApplication.model.Branch;
import com.i2i.netBankingApplication.model.CustomerTransaction;


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
 * @created 2016-09-27
 */
@Repository("transactionDao")
@Transactional
public class BeneficiaryHibernate extends GenericDaoHibernate<Beneficiary, Long> implements BeneficiaryDao {
	
	/**
     * Constructor to create a Generics-based version using Branch as the entity
     */
    public BeneficiaryHibernate() {
        super(Beneficiary.class);
    }
    
	/**
	 * Get the Beneficiary detail from CustomerService and add Beneficiary detail to database. 
	 * 
	 * @throws DataBaseException
	 *     It handle all the custom exception in NetBanking Application..
	 */
	public void insertBeneficiaryAccount(Beneficiary beneficiary) throws DataBaseException {
		try {
		    getSession().save(beneficiary);
		} catch (HibernateException e) {
			throw new DataBaseException("SORRY INFORMATION CAN'T SAVE PLEASE TRY AGAIN" + e);
		}
	}

	public List<Beneficiary> retriveAllBeneficiaries() throws DataBaseException {
		try {
		    return getSession().createQuery("FROM Beneficiary").list();
		} catch (HibernateException e) {
		    throw new DataBaseException("ACCOUNTS IS NOT AVAILABLE.");
		}
    }

	public Account retrieveAccountByNumber(String accountNumber) throws DataBaseException {
	    try {
	        return (Account)getSession().get(Account.class, accountNumber); 
	    } catch (HibernateException e) {
	    	throw new DataBaseException("OOPS SOME PROBLEM OCCURED.. PLEASE TRY AGAIN LATER");
	    }
	}
}