package com.i2i.netBankingApplication.dao;

import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.model.Account;
import com.i2i.netBankingApplication.model.Beneficiary;
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
public interface BeneficiaryDao extends GenericDao<Beneficiary, Long> {

	void insertBeneficiaryAccount(Beneficiary beneficiary) throws DataBaseException;

	Account retrieveAccountByNumber(String accountNumber) throws DataBaseException;
		
}