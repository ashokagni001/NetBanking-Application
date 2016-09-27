package com.i2i.netBankingApplication.service.impl;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.InvalidTransactionException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.netBankingApplication.dao.BeneficiaryDao;
import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.exception.TransactionCustomException;
import com.i2i.netBankingApplication.model.Account;
import com.i2i.netBankingApplication.model.Beneficiary;
import com.i2i.netBankingApplication.model.CustomerTransaction;
import com.i2i.netBankingApplication.model.User;
import com.i2i.netBankingApplication.service.BeneficiaryManager;
import com.i2i.netBankingApplication.service.UserManager;

@Service("beneficiaryManager")
public class BeneficiaryManagerImpl extends GenericManagerImpl<Beneficiary, Long> implements BeneficiaryManager {
	
	@Autowired
	private UserManager userManager;
	
    @Autowired
    private BeneficiaryDao beneficiaryDao;

    @Autowired
    public BeneficiaryManagerImpl(BeneficiaryDao beneficiaryDao) {
        super(beneficiaryDao);
        this.beneficiaryDao = beneficiaryDao;
    }
    
	public String addBeneficiaryAccount(String userId, String accountNumber, String IFSCode) 
            throws TransactionCustomException, DataBaseException {
	   // for(Beneficiary beneficiary : (userManager.getUserById(userId).getBeneficiary())) {                               
       //     if (beneficiary.getCustomerAccountNumber().getAccountNumber().equals(accountNumber)) {
              // throw new TransactionCustomException("DEAR CUSTOMER YOUR ALREADY ADDED THIS ACCOUNT IN YOUR BENEFICIARY LIST");
          //  }
	   // }
	    Account customerAccount = getCustomerAccount(accountNumber);
	    if(null != customerAccount) {
		    if(customerAccount.getBranch().getIFSCode().equals(IFSCode)) {
			    User beneficiaryUser = userManager.getUserById(userId);
			    beneficiaryDao.insertBeneficiaryAccount(new Beneficiary(beneficiaryUser, customerAccount, "request"));
			    return "YOUR ACCOUNT ADDED SUCCESSFULLY";
		    }
		    throw new TransactionCustomException("YOUR IFSCODE IS WORNG " + IFSCode);
	    }
		return IFSCode;
    }
    
	/**
     * <p>
	 *     If request comes TransactionController,get the accountNumber form Transaction Controller.
	 *     It will calling to retrieveAccountDetail method in BeneficiaryManagerImpl with accountNumber.
	 *     Return to the object of Account.
     * </p>
     * 
     * @param accountNumber
     *     accountNumber of Account to use view account detail.
     *     
     * @return Account
     *     return to the Account Detail whose accountNumber.
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application.
     */
	public Account getCustomerAccount(String accountNumber) throws DataBaseException {
		return beneficiaryDao.retrieveAccountByNumber(accountNumber);
	}

}
