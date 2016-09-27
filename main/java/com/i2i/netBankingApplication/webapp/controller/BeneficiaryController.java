package com.i2i.netBankingApplication.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.exception.TransactionCustomException;
import com.i2i.netBankingApplication.model.User;
import com.i2i.netBankingApplication.service.BeneficiaryManager;
import com.i2i.netBankingApplication.service.UserManager;



/**
 * <p>
 *     When request comes from JSP page. 
 *     TransactionController performs add new transaction or view all notifications or view all transactions or transaction 
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
public class BeneficiaryController {
	
    @Autowired
	private BeneficiaryManager beneficiaryManager;
    @Autowired
    private UserManager userManager;
   /**
	 * <p> 
	 *     This form used to view transaction by date.
	 *     It is return to the viewTransactionBYDate JSP page.
	 * </p>
	 * 
	 * @return AddTransaction
	 *     Return to the AddTransaction JSP page.
	 */
	@RequestMapping(value = "/addBeneficiary")
	public String addBeneficiaryAccountForm() {
		return "addBeneficiaryAccount";
	}
	/**
	 * <p>
	 *     This Method call to addBeneficiaryAccount method in TransactionManager.
     *     Return to the AddBeneficiaryAccount JSP page with status message(success or failure).
     * </p>
     * 
	 * @param accountNumber
	 *    accounNumber of account entered by user add new BeneficiaryAccount.
	 * @param IFSCode
	 *    IFSCode of Branch entered by user add new BeneficiaryAccount.
	 *    
	 * @return statusMessage
	 *    Return to the JSP page(AddBeneficiaryAccount Or UserHomePage) 
	 *    with status message(success or failure).
	 */
	@RequestMapping(value = "/addBeneficiaryAccount", method = RequestMethod.GET)
	public ModelAndView addBeneficiaryAccount(@RequestParam("accountNumber")String accountNumber, 
			@RequestParam("IFSCode")String IFSCode, final HttpServletRequest request) {
		try {     
			User user = userManager.getUserByUsername(request.getRemoteUser());
			System.out.println(user.getAccountNumber());
		    String userId = user.getUserId();
		    System.out.println(userId);
            return new ModelAndView ("addBeneficiaryAccount", "message", beneficiaryManager.addBeneficiaryAccount(userId, accountNumber, IFSCode)); 
    	} catch(TransactionCustomException e) {
    		return new ModelAndView ("UserHomePage", "message", e.getMessage());
    	} catch (DataBaseException e) {
    		return new ModelAndView ("UserHomePage", "message", e.getMessage());
        }
	}
	
}
