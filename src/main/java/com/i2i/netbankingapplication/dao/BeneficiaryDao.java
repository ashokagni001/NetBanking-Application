package com.i2i.netbankingapplication.dao;

import java.util.List;

import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.model.Beneficiary;

/**
 * <p>
 *   DataAccessObject(DAO) which is used to perform insert, update, retrieve, retrieve all 
 *   operations for beneficiary and return to the responses.
 *   Creates session and transaction objects for each operation.
 * </p>
 * 
 * @author Team-2
 * 
 * @created 2016-09-26
 */
public interface BeneficiaryDao extends GenericDao<Beneficiary, Long> {
    
    /**
     * <p>
     *     Saves a user's beneficiary information(insert new beneficiary account).
     * </p>
     * 
     * @param beneficiary 
     *     beneficiary holds the information of beneficiary.It is used to save.
     * 
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    void insertBeneficiary(Beneficiary beneficiary) throws DataBaseException;
    
    /**
     * </p>
     *     Gets a list of beneficiaries using database.
     * </p>    
     *  
     * @return Beneficiaries
     *     The list of beneficiaries in DB. if the beneficiaries is already persisted
     *     else return null.
     * 
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    List<Beneficiary> retrieveAllBeneficiaries() throws DataBaseException;
    
    /**
     * <p>
     *     Retrieve the beneficiary account information corresponding beneficiaryId using database.
     * </p>    
     *     
     * @param beneficiaryId
     *     beneficiaryId of Beneficiary used to retrieve the beneficiary account.
     * 
     * @return beneficiary information based on beneficiaryId.
     * 
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    Beneficiary retrieveBeneficiaryAccount(int beneficiaryId) throws DataBaseException;
    
    /**
     * <p>
     *     Update the beneficiary information to corresponding record using database.
     * </p>
     * 
     * @param beneficiary
     *     beneficiary holds the information of beneficiary.It is used to update.
     *    
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    void updateBeneficiary(Beneficiary beneficiary) throws DataBaseException;
}
