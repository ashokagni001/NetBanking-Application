package com.i2i.netBankingApplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.netBankingApplication.dao.BeneficiaryDao;
import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.exception.TransactionCustomException;
import com.i2i.netBankingApplication.model.Account;
import com.i2i.netBankingApplication.model.Beneficiary;
import com.i2i.netBankingApplication.model.Branch;
import com.i2i.netBankingApplication.model.User;
import com.i2i.netBankingApplication.service.BeneficiaryManager;
import com.i2i.netBankingApplication.service.BranchManager;

@Service("beneficiaryManager")
public class BeneficiaryManagerImpl extends GenericManagerImpl<Beneficiary, Long> implements BeneficiaryManager{
    
    @Autowired
    BeneficiaryDao beneficiaryDao;
    
    @Autowired
    private BranchManager branchManager;
    
    public String addBeneficiaryAccount(User user, String accountNumber, String IFSCode) throws TransactionCustomException, DataBaseException {
        Branch branch = branchManager.getBranchByIFSCode(IFSCode);
        if (null == branch) {
            throw new TransactionCustomException("Enter valid beneficiary IFSC");
        }
        
        Account account = branchManager.getAccountByAccountNumber(accountNumber);
        if (null == account) {
            throw new TransactionCustomException("Enter valid beneficiary account number");
        }
        
        beneficiaryDao.insertBeneficiary(new Beneficiary(user, account, "Request"));
        return ("YOUR REQUEST SEND ADMIN PROCESS WITHIN 8 HOUR'S GET YOUR UPDATE");
    }
}
