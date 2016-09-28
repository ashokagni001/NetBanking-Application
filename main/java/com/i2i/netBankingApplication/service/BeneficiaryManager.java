package com.i2i.netBankingApplication.service;

import java.util.List;

import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.exception.TransactionCustomException;
import com.i2i.netBankingApplication.model.User;

public interface BeneficiaryManager {
    
    String addBeneficiaryAccount(User user, String accountNumber, String IFSCode) throws TransactionCustomException, DataBaseException;

	List getAllBeneficiaries() throws TransactionCustomException, DataBaseException;

	void beneficiaryAccountActive(int beneficiaryId) throws DataBaseException;

	void beneficiaryAccountDeactive(int beneficiaryId) throws DataBaseException;

    List getBeneficiaryAccountByCustomerId(User user) throws TransactionCustomException, DataBaseException;
    
   // String addTransactionDetail(String customerId, String creditAccountNumber, double amount) throws TransactionCustomException, DataBaseException;
    
   // Account retrieveAccountByNumber(String accountNumber) throws DataBaseException;
}
