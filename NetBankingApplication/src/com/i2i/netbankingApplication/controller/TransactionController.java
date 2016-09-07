package com.i2i.netbankingApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.model.Account;
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
    		@RequestParam("criditAccountNumber") String criditAccountNumber, 
    		@RequestParam("amount")String amount,ModelMap message) {  
		try {
			transactionService.getTransactionDetail(debitAccountNumber, criditAccountNumber, 
					Double.parseDouble(amount));
		} catch (DataBaseException e) {
            System.out.println("ENTER VALID DATA ONLY" + e); 
        }
		return "AddTransaction";
    }

}
