package com.i2i.netbankingapplication.dao;

import java.util.List;

import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.model.Account;
import com.i2i.netbankingapplication.model.CustomerTransaction;

/**
 * <p>
 *     When request comes from CustomerTransactionManager. CustomerTransactionDao performs add or delete or fetch or fetchAll 
 *     with database and return the responses to CustomerTransactionManager.
 *     It handles the NullPointerException, NumberFormatException and HibernateException.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-29
 */
public interface CustomerTransactionDao extends GenericDao<CustomerTransaction, Long> {
    
    /**
     * <p>
     *     Get the transaction information and account information from CustomerTransactionManager.
     *     Add new transaction information and  update the account information to database. 
     * </p> 
     *  
     * @param customerTransaction
     *     New customerTransaction information.It is used to add new record.
     * @param debitAccount
     *     update the account information. It is used to update the record.
     *     
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    void insertTransaction(CustomerTransaction customerTransaction, Account debitAccount) throws DataBaseException; 
    
    /**
     * <p>
     *     Retrieves the all Customer transactions from database.
     *     Return all list of customer transactions.
     * </p>
     * 
     * 
     * @return list of customer transactions.
     * 
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    List<CustomerTransaction> retriveAllTransactions() throws DataBaseException;
    
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
    CustomerTransaction retriveTransactionById(int id) throws DataBaseException;
    
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
    void updateTransactionStatus(Account account,CustomerTransaction customerTransaction) throws DataBaseException; 
}
