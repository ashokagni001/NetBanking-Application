package com.i2i.netbankingapplication.dao.hibernate;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.i2i.netbankingapplication.dao.CustomerTransactionDao;
import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.model.Account;
import com.i2i.netbankingapplication.model.CustomerTransaction;
import com.i2i.netbankingapplication.util.StringUtil;


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
     * Constructor to create a Generics-based version using CustomerTransaction as the entity
     */
    public CustomerTransactionHibernate() {
        super(CustomerTransaction.class);
    }
    
    /**
     * <p>
     *     Get the transaction information and account information from CustomerTransactionManager.
     *     Add new transaction information and  update the account information to database. 
     * </p> 
     *  
     * @param customerTransaction
     *     New customerTransaction information.It is used to add new record.
     * @param debitAccount
     *     debitAccount of Account. It is used to update the record.
     *     
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    public void insertTransaction(CustomerTransaction customerTransaction, Account debitAccount) throws DataBaseException{
        try {
            Session session = getSession();
            session.save(customerTransaction);
            session.update(debitAccount);
        } catch (HibernateException e) {
            throw new DataBaseException(StringUtil.informationReader().getProperty("insertTransactionError"));  
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
        try {
            return getSession().createQuery("FROM CustomerTransaction").list();
        } catch (HibernateException e) {
            throw new DataBaseException(StringUtil.informationReader().getProperty("viewAllTransactionsError"));
        }
    }

    /**
     * <p>
     *     Retrieves the all Customer transactions based on id from database.
     *     Return customer transaction based on transaction id.
     * </p>
     * 
     * @param id
     *     id of transaction. It is used to view corresponding record.
     *     
     * @return customer transaction information based on id.
     * 
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    public CustomerTransaction retriveTransactionById(int id) throws DataBaseException {
        return (CustomerTransaction)getSession().get(CustomerTransaction.class, id); 
    }
    
    /**
     * <p>
     *     Update the account information and customer transaction information to corresponding record using database.
     * </p>
     * 
     * @param account
     *     update the account information. It is used to update the record.
     * @param customerTransaction
     *     update the customerTransaction information. It is used to update the record.
     *     
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    public void updateTransactionStatus(Account account,CustomerTransaction customerTransaction) 
            throws DataBaseException {
        try {
            Session session = getSession();
            session.update(account);
            session.update(customerTransaction);
        } catch (HibernateException e) {
            throw new DataBaseException(StringUtil.informationReader().getProperty("updateTransactionErrorMessage"));
        }
    }
}
