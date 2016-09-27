package com.i2i.netBankingApplication.dao;

import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.model.Account;
import com.i2i.netBankingApplication.model.Beneficiary;
import com.i2i.netBankingApplication.model.CustomerTransaction;

/**
 * <p>
 *     When request comes from TransactionManager. TransactionDao performs add or delete or fetch or fetchAll 
 *     with database and return the responses to TransactionManager.
 * </p>
 * 
 * @author ASHOK
 * 
 * @created 2016-09-27
 */
public interface TransactionDao extends GenericDao<CustomerTransaction, Long> {

    void insertBeneficiaryAccount(Beneficiary beneficiary) throws DataBaseException;

    Account retrieveAccountByNumber(String accountNumber) throws DataBaseException;
        
}
