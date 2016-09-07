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
	
	@RequestMapping(value = "/TransactionOperation")
	public String transactionOpration() throws DataBaseException {
		//transactionService.getTransactionDetail("123456", "234567", 500);
		return "TransactionOperation";
	}
	
	@RequestMapping(value="/insertTransaction", method = RequestMethod.POST)
    public String transaction(@RequestParam("debitAccountNumber") String debitAccountNumber, 
    		@RequestParam("criditAccountNumber") String criditAccountNumber, 
    		@RequestParam("amount")String amount,ModelMap message) {  
		try {
			System.out.println(amount);
			transactionService.getTransactionDetail(debitAccountNumber, criditAccountNumber, Double.parseDouble(amount));
		} catch (DataBaseException e) {
            System.out.println("ENTER VALID DATA ONLY" + e); 
        }
		return "CustomerOperation";
    }

}
