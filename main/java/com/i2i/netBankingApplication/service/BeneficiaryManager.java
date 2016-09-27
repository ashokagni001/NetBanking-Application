package com.i2i.netBankingApplication.service;

import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.exception.TransactionCustomException;
import com.i2i.netBankingApplication.model.Account;
import com.i2i.netBankingApplication.model.Beneficiary;

public interface BeneficiaryManager extends GenericManager<Beneficiary, Long> {

    String addBeneficiaryAccount(String userId, String accountNumber, String IFSCode) 
    		throws TransactionCustomException, DataBaseException;
    
    Account getCustomerAccount(String accountNumber) throws DataBaseException;

}
