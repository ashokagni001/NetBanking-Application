package com.i2i.netBankingApplication.service;

import java.util.List;

import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.exception.TransactionCustomException;
import com.i2i.netBankingApplication.model.Account;
import com.i2i.netBankingApplication.model.User;

public interface TransactionManger {
    
    String addBeneficiaryAccount(User user, String accountNumber, String IFSCode) throws TransactionCustomException, DataBaseException;
    
   // List getBeneficiaryAccountByCustomerId(String customerId) throws TransactionCustomException, DataBaseException;
    
   // String addTransactionDetail(String customerId, String creditAccountNumber, double amount) throws TransactionCustomException, DataBaseException;
    
   // Account retrieveAccountByNumber(String accountNumber) throws DataBaseException;
}
