package com.i2i.netBankingApplication.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.exception.TransactionCustomException;
import com.i2i.netBankingApplication.model.User;
import com.i2i.netBankingApplication.service.BeneficiaryManager;
import com.i2i.netBankingApplication.service.CustomerTransactionManager;
import com.i2i.netBankingApplication.service.UserManager;

/**
 * <p>
 *     When request comes from JSP page. 
 *     TransactionController performs add new beneficiary or view all notifications or view all transactions or transaction 
 *     permission or transaction ignore with model(transaction), Manager(transaction) and return the responses to JSP page.
 *     It handles the NumberFormatException, DataBaseException.
 *     If exception occurs it will write what type of exception occurred.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-27
 */
@Controller
public class CustomerTransactionController {
	
    @Autowired
	private CustomerTransactionManager customerTransactionManager;
    
    @Autowired
    private UserManager userManager;
    
    /**
	 * <p>
	 *     It is return to the AddTransaction JSP page.
	 *     This form used to user transaction.
	 * </p>
	 * 
	 * @return AddTransaction
	 *     Return to the AddTransaction JSP page.
	 */ 
	@RequestMapping(value = "/addTransaction")
	public String transactionForm() {
		return "addTransaction";
	}
	
	/**
	 * <p>
     *     Get the transaction detail from JSP page and pass to getTransactionDetail method in transactionService.
     *     Return to JSP page transactionRegistration with status message(success or failure) Or Address object.
     * </p>
     * 
	 * @param debitAccountNumber
	 *     debitAccountNumber of Transaction.
	 * @param creditAccountNumber
	 *     creditAccountNumber of Transaction
	 * @param ifscode
	 *     ifsCode of Branch.
	 * @param amount
	 *     amount of transaction.
	 * @param message
	 *     Display message using add attribute.
	 *     
	 * @return UserHomePage
     *     Return to JSP page UserHomePage with status message(success or failure).
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application.
     */
	
	@RequestMapping(value = "/insertTransaction", method = RequestMethod.GET)
	public String addTransaction(@RequestParam("creditAccountNumber") String creditAccountNumber,
			@RequestParam("amount") String amount, ModelMap message, final HttpServletRequest request) {
		try {
			message.addAttribute("message", customerTransactionManager.addTransactionDetail(userManager.getUserByUsername(request.getRemoteUser()) ,
					creditAccountNumber, Double.parseDouble(amount)));
		} catch (TransactionCustomException e) {
			message.addAttribute("message", e.getMessage());
		} catch (DataBaseException e) {
			message.addAttribute("message", e.getMessage());
		} finally {
			return "addTransaction";
		}
	}
	
}