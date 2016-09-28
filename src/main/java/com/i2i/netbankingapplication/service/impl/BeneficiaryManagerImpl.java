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

@Service("beneficiaryManager")
public class BeneficiaryManagerImpl extends GenericManagerImpl < Beneficiary, Long > implements BeneficiaryManager {

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
        return ("YOUR REQUEST SEND ADMIN PROCESS WITHIN FEW HOURS GET YOUR UPDATE");
    }

    public List getAllBeneficiaries() throws TransactionCustomException, DataBaseException {
        List < Beneficiary > beneficiaries = new ArrayList < Beneficiary > ();
        for (Beneficiary beneficiary: beneficiaryDao.retrieveAllBeneficiaries()) {
            if (beneficiary.getStatus().equals("Request")) {
                beneficiaries.add(beneficiary);
            }
        }
        if (Constants.SIZE != beneficiaries.size()) {
            return beneficiaries;
        } else {
            throw new TransactionCustomException("NO BENEFICIARY NOTIFICATION AVAILABLE");
        }
    }

    public void beneficiaryAccountActive(int beneficiaryId) throws DataBaseException {
        beneficiaryDao.beneficiaryAccountActive(beneficiaryId);
    }

    public void beneficiaryAccountDeactive(int beneficiaryId) throws DataBaseException {
        beneficiaryDao.beneficiaryAccountDeactive(beneficiaryId);
    }

    public List < Beneficiary > getBeneficiaryAccountByCustomerId(User user) throws TransactionCustomException, DataBaseException {
        List < Beneficiary > customerBeneficiary = new ArrayList < Beneficiary > ();
        for (Beneficiary beneficiary: beneficiaryDao.retrieveAllBeneficiaries()) {
            if (beneficiary.getBeneficiaryAccountNumber().equals(user.getAccountNumber())) {
                if (!((beneficiary.getStatus().equals("Request")) | (beneficiary.getStatus().equals("Failure")))) {
                    customerBeneficiary.add(beneficiary);
                }
                if (null == customerBeneficiary) {
                    throw new TransactionCustomException("YOUR BENEFICIARY ACCOUNT NOT AVAILABLE..INSERT NEW BENEFICIARY ACCOUNT");
                }
            }
        }
        return customerBeneficiary;
    }
}
