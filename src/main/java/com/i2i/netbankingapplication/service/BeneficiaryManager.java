package com.i2i.netbankingapplication.service;

import java.util.List;

import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.exception.TransactionCustomException;
import com.i2i.netbankingapplication.model.User;

public interface BeneficiaryManager {

    String addBeneficiaryAccount(User user, String accountNumber, String IFSCode) throws TransactionCustomException, DataBaseException;

    List getAllBeneficiaries() throws TransactionCustomException, DataBaseException;

    void beneficiaryAccountActive(int beneficiaryId) throws DataBaseException;

    void beneficiaryAccountDeactive(int beneficiaryId) throws DataBaseException;

    List getBeneficiaryAccountByCustomerId(User user) throws TransactionCustomException, DataBaseException;
}
