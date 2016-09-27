package com.i2i.netBankingApplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.netBankingApplication.Constants;
import com.i2i.netBankingApplication.dao.BeneficiaryDao;
import com.i2i.netBankingApplication.dao.CustomerTransactionDao;
import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.exception.TransactionCustomException;
import com.i2i.netBankingApplication.model.Account;
import com.i2i.netBankingApplication.model.CustomerTransaction;
import com.i2i.netBankingApplication.model.User;
import com.i2i.netBankingApplication.service.BeneficiaryManager;
import com.i2i.netBankingApplication.service.BranchManager;
import com.i2i.netBankingApplication.service.CustomerTransactionManager;
import com.i2i.netBankingApplication.service.UserManager;

@Service("customerTransactionManager")
public class CustomerTransactionManagerImpl extends GenericManagerImpl<CustomerTransaction, Long> 
         implements CustomerTransactionManager {
	 @Autowired
	 private BeneficiaryManager beneficiaryManager; 
	 
	 @Autowired
	 private CustomerTransactionDao customerTransactionDao;
	
	 @Autowired
	 private BranchManager branchManager; 
	 
	/**
	 * <p> 
     *     Get the Transaction detail from TransactionController.
     *     It is passed to addTransaction method in TransactionDao and 
     *     If exception occurs return error message to TransactionController.
     * </p>
     * 
	 * @param debitAccountNumber
	 *     debitAccountNumber of Transaction to use add new Transaction.
	 * @param creditAccountNumber
	 *     creditAccountNumber of Transaction to use add new Transaction.
	 * @param amount
	 *     amount of Transaction to use add new Transaction.
	 *      
	 * @throws DataBaseException
	 *     It handle all the custom exception in NetBanking Application.
	 */
	public String addTransactionDetail(User user, String creditAccountNumber, 
			double amount) throws TransactionCustomException, DataBaseException {
		Account debitAccount = getAccountByAccountNumber(user.getAccountNumber());
		double currentAmount = debitAccount.getBalance();
		double balanceAmount = (currentAmount - amount);
		//check the balance amount valid or not.
		if (balanceAmount > Constants.SIZE) {
		    debitAccount.setBalance(balanceAmount);
			customerTransactionDao.insertTransaction(new CustomerTransaction(debitAccount, 
					branchManager.getAccountByAccountNumber(creditAccountNumber), amount, "request"), debitAccount);
			return "YOUR TRANSACTION DETAIL SEND OUR TRANSACTION APPROVER PLEASE WAIT";
		} else {
			throw new TransactionCustomException("YOUR CREDIT AMOUNT VALUE IS MUST BE LESSER THAN CURRENT AMOUNT :Rs " + currentAmount );
		}
	}
	
	/**
	 * <p>
	 *     If request comes TransactionController, It will calling to getCustomerById and getCustomerAccount method in TransactionService.
	 *     Return to the object of Account,
     * </p>
	 * 
	 * @param customerId
	 *     customerId of Customer to use get the CustomerAccountNumber.
	 *     
	 * @return Account
	 *     Return Account Detail.
	 *     
	 * @throws DataBaseException
	 *     It handle all the custom exception in NetBanking Application.
	 */
	public Account getAccountByAccountNumber(String accountNumber) throws DataBaseException {
		return branchManager.getAccountByAccountNumber(accountNumber);
	}

}
