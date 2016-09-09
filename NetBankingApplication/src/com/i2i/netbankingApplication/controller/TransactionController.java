package com.i2i.netbankingApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.service.TransactionService;
import com.i2i.netbankingApplication.util.FileUtil;


/**
 * <p>
 *     When request comes from JSP page. 
 *     transaction controller performs add or notification or fetchAll 0r permission or ignore transaction with model(transaction), 
 *     service(transaction) and return the responses to JSP page.
 *     It handles the NumberFormatException, DataBaseException.
 *     If exception occurs it will write what type of exception occurred.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-03
 */
@Controller
public class TransactionController {
	TransactionService transactionService = new TransactionService();
	
	/**
     * Return the JSP page that contains options for transaction operation
     * 
     * @return transactionIndex
     *     Return to the transactionIndex JSP page.
     */
	@RequestMapping(value = "/TransactionIndex")
    public String transactionIndex() {
	    return "TransactionIndex";
    }
	 
    /**
	 * <p>
	 *     It displays a form to input data, here "transaction" is a reserved attribute which is used
	 *     to display object data(transaction) into form.
	 * </p>
	 * 
	 * @param model
	 *     transaction model return the transaction object.
	 * 
	 * @return AddTransaction
	 *     Return to the AddTransaction JSP page with model(transaction) object.
	 */	
	@RequestMapping(value = "/addTransaction")
	public String transaction() {
		return "AddTransaction";
	}
	

	/**
     * <p>
     *    Get the transaction object from JSP page and pass to gettransaction method in transactionService.
     *    Return to JSP page transactionRegistration with status message(success or failure) Or Address object.
     * </p>
     * 
     * @param transaction
     *     Object of transaction model class.
     * 
     * @param message
     *     Display message using add attribute.
     * 
     * @return transactionRegistration
     *     Return to JSP page transactionRegistration with status message(success or failure).
     * 
     * @throws NumberFormatException
     *     If the data is very large.
     * 
     * @throws DataBaseException
     *     If there is an error in the given data like BadElementException.
     */
    @RequestMapping(value="/insertTransaction", method = RequestMethod.POST)
	public String insertTransaction(@RequestParam("debitAccountNumber") String debitAccountNumber, 
	    @RequestParam("criditAccountNumber") String criditAccountNumber, @RequestParam("ifscode")String ifscode ,
	    @RequestParam("amount")String amount, ModelMap message) {  
		try {
			transactionService.getTransactionDetail(debitAccountNumber, criditAccountNumber, ifscode, Double.parseDouble(amount));
		} catch (NumberFormatException e) {
			FileUtil.exceptionOccurCreateFile("transaction ADD AT TIME EXCEPTION OCCUR..\nDATAS-->" + debitAccountNumber +
				     criditAccountNumber + ifscode + e );
			message.addAttribute("message", "YOUR DATAS IS VERY LARGE.. ENTER VALID DATA"); 
		} catch (DataBaseException e) {
			FileUtil.exceptionOccurCreateFile("TRANSACTION ADD AT TIME EXCEPTION OCCUR ..\nDATAS-->" +
		            debitAccountNumber + criditAccountNumber + ifscode + amount + e);
	        message.addAttribute("message", e);
	    }
		return "transactionOperation";
	}
	
    /**
	 * <p>
	 *    This Method call to getAllNotification method in transactionService.
     *    Return to the RetrieveAllNotification JSP page with notification lists or status message(failure).
     * </p>
     * 
	 * @param transactionId
	 *     Id of transaction entered by user to view the corresponding record.
	 *     
	 * @return RetrieveAllNotification
	 *     Return to the RetrieveAllNotification JSP page with notification lists or status message(failure).
	 */
	@RequestMapping(value="/notification", method = RequestMethod.GET)
    public ModelAndView notification() {
    	try {                     
            return new ModelAndView ("RetrieveAllNotification", "notifications", transactionService.getAllNotification()); 
    	} catch (DataBaseException e) {
    		FileUtil.exceptionOccurCreateFile("NOTIFICATION VIEW AT TIME EXCEPTION OCCUR");
    		return new ModelAndView ("RetrieveAllNotification", "message", e.getMessage().toString());
        }
	}
	
	/**
	 * <p>
	 *    This Method call to getAllTransaction method in transactionService.
     *    Return to the RetrieveAllTransaction JSP page with transaction lists or status message(failure).
     * </p>
	 *     
	 * @return RetrieveAllTransaction
	 *     Return to the RetrieveAllTransaction JSP page with transaction lists or status message(failure).
	 */
	@RequestMapping(value="/viewAllTransaction", method = RequestMethod.GET)
    public ModelAndView viewAllTransaction() {
    	try {                     
            return new ModelAndView ("RetrieveAllTransaction", "transactions", transactionService.getAllTransaction()); 
    	} catch (DataBaseException e) {
    		FileUtil.exceptionOccurCreateFile("NOTIFICATION VIEW AT TIME EXCEPTION OCCUR");
    		return new ModelAndView ("RetrieveAllTransaction", "message", e.getMessage().toString());
        }
	}
	
	/**
	 * <p>
	 *    This Method call to getAddressById method in transactionService.
     *    Return to the RetrieveAlltransaction JSP page with transaction lists or status message(failure).
     * </p>
     * 
	 * @param addressId
	 *     Id of address entered by user to view the corresponding record.
	 *     
	 * @return RetrieveAddressById
	 *     Return to the RetrieveAddressById JSP page with transaction account detail or status message(failure).
	 */
	@RequestMapping(value="/viewtransactionAccount", method = RequestMethod.GET)  
    public ModelAndView viewtransactionAccount (@RequestParam("accountNumber")String accountNumber, ModelMap message) {
        try {
            return new ModelAndView("RetrievetransactionAccount", "accountDetail", transactionService.getTransactionById (accountNumber));
        } catch (DataBaseException e) {
        	FileUtil.exceptionOccurCreateFile("transaction ACCOUNT VIEW AT TIME EXCEPTION OCCUR... ACCOUNT NUMBER:" + accountNumber);
        	return new ModelAndView("RetrievetransactionAccount", "message", "ENTER VALID IFSC ONLY");
        }
    }
	

	/**
	 * It return to the  viewAllTransaction JSP page.
	 * It form used for approver permission active.
	 * 
	 * This Method call to transactionSuccess( method in transactionService.
	 *  
	 * @return viewAllTransaction
	 *     Return to the viewAllTransaction JSP page.
	 */ 
	@RequestMapping(value="/transactionSuccess", method = RequestMethod.GET)
    public String transactionSuccess(@RequestParam("id")int transactionId,
    		@RequestParam("criditAccountNumber")String criditAccountNumber, @RequestParam("amount")Double amount, ModelMap message) {
    	try {           
    		transactionService.transactionSuccess(transactionId, criditAccountNumber, amount);
    		message.addAttribute("message", "TRANSACTION ACTION SUCCESSFULLY"); 
    	} catch (DataBaseException e) {
        	FileUtil.exceptionOccurCreateFile("TRANSACTION PERMISSION AT TIME EXCEPTION OCCUR...DATAS" + transactionId + criditAccountNumber +amount);
    		message.addAttribute( "message", e.getMessage().toString());
        }
		return "viewAllTransaction";
	}
	

	/**
	 * It return to the  viewAllTransaction JSP page with status (Success or Failure).
	 * It form used for approver ignore active.
	 *  
	 * @return viewAllTransaction
	 *     Return to the viewAllTransaction JSP page.
	 */ 
	@RequestMapping(value="/transactionCancel", method = RequestMethod.GET)
    public String transactionFailure(@RequestParam("id")int transactionId,
    		@RequestParam("debitAccountNumber")String debitAccountNumber, @RequestParam("amount")Double amount, ModelMap message) {
    	try {           
    		transactionService.transactionFailure(transactionId, debitAccountNumber, amount);
            message.addAttribute("transactions", "TRANSACTION ACTION SUCCESSFULLY"); 
    	} catch (DataBaseException e) {
    		FileUtil.exceptionOccurCreateFile("TRANSACTION IGNORE AT TIME EXCEPTION OCCUR...DATAS" + transactionId + debitAccountNumber + amount);
    		message.addAttribute("message", e.getMessage().toString());
        }
		return "viewAllTransaction";
	}
}
