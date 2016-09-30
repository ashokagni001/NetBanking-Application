package com.i2i.netbankingapplication.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.netbankingapplication.Constants;
import com.i2i.netbankingapplication.dao.BeneficiaryDao;
import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.exception.TransactionCustomException;
import com.i2i.netbankingapplication.model.Account;
import com.i2i.netbankingapplication.model.Beneficiary;
import com.i2i.netbankingapplication.model.Branch;
import com.i2i.netbankingapplication.model.User;
import com.i2i.netbankingapplication.service.BeneficiaryManager;
import com.i2i.netbankingapplication.service.BranchManager;
import com.i2i.netbankingapplication.util.StringUtil;

/**
 * <p>
 *     When request comes from BeneficiaryController. 
 *     BeneficiaryManager performs add or fetch or fetchAll or update beneficiary with model(Beneficiary),
 *     DAO(CustomerTransaction) and return the responses to CustomerTransactionController.
 *     BeneficiaryManager operate passing value's to BeneficiaryDao based on requset's from BeneficiaryController.
 *     If method handle the TransactionCustomException. It is means our business logic custom exception.
 *     It service validate the business logics using Constants class.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-29.
 */
@Service("beneficiaryManager")
public class BeneficiaryManagerImpl extends GenericManagerImpl<Beneficiary, Long> implements BeneficiaryManager{
    
    @Autowired
    BeneficiaryDao beneficiaryDao;
    
    @Autowired
    private BranchManager branchManager;
    
    /**
     * <p> 
     *     Get the beneficiary account detail.
     *     If exception occurs return error message to BeneficiaryController.
     * </p>
     * 
     * @param user
     *     user holds the information of user.
     * @param accountNumber
     *     accountNumber of Account used to verify the valid number or not.
     * @param ifscode
     *     ifscode of Branch used to verify the branch valid branch or not.
     *     
     * @return status message (success or failure).
     * 
     * @throws TransactionCustomException
     *     It handle all the custom exception in NetBanking Application.
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    public String addBeneficiaryAccount(User user, String accountNumber, String ifscode) throws TransactionCustomException, DataBaseException {
        Branch branch = branchManager.getBranchByIfscode(ifscode);
        if (null == branch) {
            throw new TransactionCustomException("Enter valid beneficiary ifscode");
        }
        Account account = branchManager.getAccountByAccountNumber(accountNumber);
        if (null == account) {
            throw new TransactionCustomException("Enter valid beneficiary account number");
        }
        beneficiaryDao.insertBeneficiary(new Beneficiary(user, account, Constants.STATUS_REQUEST));
        return StringUtil.informationReader().getProperty("beneficiaryRequest");
    }
    
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
    public List<Beneficiary> getAllBeneficiaries() throws TransactionCustomException, DataBaseException {
        List<Beneficiary> beneficiaries = new ArrayList<Beneficiary>();
        for (Beneficiary beneficiary : beneficiaryDao.retrieveAllBeneficiaries()) {
            if (beneficiary.getStatus().equals(Constants.STATUS_REQUEST)) {
                beneficiaries.add(beneficiary);
            } 
        }
        if (Constants.SIZE != beneficiaries.size()) {
             return beneficiaries;
        } else {
            throw new TransactionCustomException(StringUtil.informationReader().getProperty("beneficiaryExceptionMessage"));
        }
    }
    
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
    public void updateBeneficiaryAccount(int beneficiaryId, String action) throws DataBaseException {
        Beneficiary beneficiary = beneficiaryDao.retrieveBeneficiaryAccount(beneficiaryId);
        beneficiary.setStatus(action);
        beneficiaryDao.updateBeneficiary(beneficiary);
    }
    
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
    public List<Beneficiary> getBeneficiaryAccountByCustomerId(User user) throws TransactionCustomException, DataBaseException {
        List<Beneficiary> customerBeneficiary = new ArrayList<Beneficiary>();
        for (Beneficiary beneficiary : beneficiaryDao.retrieveAllBeneficiaries()) {
            if(beneficiary.getBeneficiaryAccountNumber().getAccountNumber().equals(user.getAccountNumber())) {
                if (beneficiary.getStatus().equals(Constants.STATUS_SUCCESS)) {
                    customerBeneficiary.add(beneficiary);
                }
                if(null == customerBeneficiary) {
                    throw new TransactionCustomException(StringUtil.informationReader().getProperty("customerBeneficiaryMessage"));
                }
            }
        }
        return customerBeneficiary;
    }
}
