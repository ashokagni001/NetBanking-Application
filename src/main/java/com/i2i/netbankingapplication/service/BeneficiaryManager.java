package com.i2i.netbankingapplication.service;

import java.util.List;

import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.exception.TransactionCustomException;
import com.i2i.netbankingapplication.model.Beneficiary;
import com.i2i.netbankingapplication.model.User;

/**
 * <p>
 *     When request comes from CustomerTransactionController. 
 *     CustomerTransactionManager performs add or fetch or fetchAll or update transaction with model(CustomerTransaction),
 *     dao(CustomerTransaction) and return the responses to CustomerTransactionController.
 *     CustomerTransactionManager operate passing value's to CustomerTransactionDao based on requset's from CustomerTransactionController.
 *     If method handle the TransactionCustomException. It is means our business logic custom exception.
 *     It service validate the business logics using Constants class.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-29.
 */
public interface BeneficiaryManager {
    
    /**
     * <p> 
     *     Get the beneficiary account detail.
     *     If exception occurs return error message to BeneficiaryController.
     *     If method handle the TransactionCustomException. It is means our business logic custom exception.
     * </p>
     * 
     * @param user
     *     user holds the information of user.
     * @param accountNumber
     *     accountNumber of account to used add new beneficiary account.
     * @param ifscode
     *     ifscode of Branch used to verify the branch valid branch or not
     * @return status message (success or failure).
     * @throws TransactionCustomException
     *     It handle all the custom exception in NetBanking Application.
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    String addBeneficiaryAccount(User user, String accountNumber, String ifscode) throws TransactionCustomException, DataBaseException;
    
    /**
     * <p>
     *     If request comes BeneficiaryController, It will calling to retrieveAllBeneficiaries method in BeneficiaryDao.
     *     Return to the lists of Customer Beneficiaries.
     *     If method handle the TransactionCustomException. It is means our business logic custom exception.
     * </p>
     * 
     * @return list of beneficiaries.
     * 
     * @throws TransactionCustomException
     *     It handle all the custom exception in NetBanking Application.
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    List<Beneficiary> getAllBeneficiaries() throws TransactionCustomException, DataBaseException;
    
    /**
     * <p>
     *     This method update the beneficiary account information based on beneficiaryId.
     *     This method retrieve the beneficiary account information based on beneficiaryId 
     *     using retrieveBeneficiaryAcount using BeneficiaryDao.  
     * </p>
     * 
     * @param beneficiaryId
     *     beneficiaryId of Beneficiary used to update the beneficiary account information.
     * @param action
     *     contains String of action used to update the transaction.
     *     
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    void updateBeneficiaryAccount(int beneficiaryId, String action) throws DataBaseException;
    
    /**
     * <p>
     *     If request comes BeneficiaryController, It will calling to retrieveAllBeneficiaries method in BeneficiaryDao.
     *     Return to the lists of Customer Beneficiaries based on userId.
     *     If method handle the TransactionCustomException. It is means our business logic custom exception.
     * </p>
     * 
     * @param user
     *     user holds the information of user.
     *     
     * @return list of beneficiaries based on userId.
     * 
     * @throws TransactionCustomException
     *     It handle all the custom exception in NetBanking Application.
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    List<Beneficiary> getBeneficiaryAccountByCustomerId(User user) throws TransactionCustomException, DataBaseException;
    
}
