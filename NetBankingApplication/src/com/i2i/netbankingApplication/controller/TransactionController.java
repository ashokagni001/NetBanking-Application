package com.i2i.netbankingApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.service.TransactionService;

@Controller
public class TransactionController {
	TransactionService transactionService = new TransactionService();
	
	@RequestMapping(value = "/TransactionIndex")
    public String customer() {
	    return "TransactionIndex";
    }
	 
	@RequestMapping(value = "/addTransaction")
	public String transactionOpration() throws DataBaseException {
		return "AddTransaction";
	}
	
    @RequestMapping(value="/insertTransaction", method = RequestMethod.POST)
	public String transaction(@RequestParam("debitAccountNumber") String debitAccountNumber, 
	    @RequestParam("criditAccountNumber") String criditAccountNumber, @RequestParam("ifscode")String ifscode ,
	    @RequestParam("amount")String amount,ModelMap message) {  
		try {
			message.addAttribute("message", transactionService.getTransactionDetail(debitAccountNumber, criditAccountNumber, ifscode, Double.parseDouble(amount)));
		} catch (DataBaseException e) {
			message.addAttribute("message", e.toString()); 
	    } finally {
		    return "TransactionIndex";
	    }
	}
	
	@RequestMapping(value="/notification", method = RequestMethod.GET)
    public ModelAndView notification() {
    	try {                     
            return new ModelAndView ("RetrieveAllNotification", "notifications", transactionService.getAllNotification()); 
    	} catch (DataBaseException e) {
    		return new ModelAndView ("RetrieveAllNotification", "message", e.getMessage().toString());
        }
	}
	
	@RequestMapping(value="/viewAllTransaction", method = RequestMethod.GET)
    public ModelAndView viewAllTransaction() {
    	try {                     
            return new ModelAndView ("RetrieveAllTransaction", "transactions", transactionService.getAllTransaction()); 
    	} catch (DataBaseException e) {
    		return new ModelAndView ("RetrieveAllTransaction", "message", e.getMessage().toString());
        }
	}
	
	@RequestMapping(value="/viewCustomerAccount", method = RequestMethod.GET)  
    public ModelAndView viewCustomerAccount (@RequestParam("accountNumber")String accountNumber, ModelMap message) {
        try {
            return new ModelAndView("RetrieveCustomerAccount","accountDetail", transactionService.getCustomerAccount (accountNumber));
        } catch (DataBaseException e) {
        	return new ModelAndView("RetrieveCustomerAccount","message", "ENTER VALID IFSC ONLY");
        }
    }
	
	@RequestMapping(value="/transactionSuccess", method = RequestMethod.GET)
    public String transactionSuccess(@RequestParam("id")int transactionId,
    		@RequestParam("criditAccountNumber")String criditAccountNumber, @RequestParam("amount")Double amount, ModelMap message) {
    	try {           
    		transactionService.transactionSuccess(transactionId, criditAccountNumber, amount);
    		message.addAttribute("message", "TRANSACTION ACTION SUCCESSFULLY"); 
    	} catch (DataBaseException e) {
    		message.addAttribute( "message", e.getMessage().toString());
        }
		return "viewAllTransaction";
	}
	
	@RequestMapping(value="/transactionCancel", method = RequestMethod.GET)
    public String transactionFailure(@RequestParam("id")int transactionId,
    		@RequestParam("debitAccountNumber")String debitAccountNumber, @RequestParam("amount")Double amount, ModelMap message) {
    	try {           
    		transactionService.transactionFailure(transactionId, debitAccountNumber, amount);
            message.addAttribute("transactions", "TRANSACTION ACTION SUCCESSFULLY"); 
    	} catch (DataBaseException e) {
    		message.addAttribute("message", e.getMessage().toString());
        }
		return "viewAllTransaction";
	}
}
