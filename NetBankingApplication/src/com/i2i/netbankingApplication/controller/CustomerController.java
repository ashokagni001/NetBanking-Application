package com.i2i.netbankingApplication.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.model.Address;
import com.i2i.netbankingApplication.model.Customer;
import com.i2i.netbankingApplication.service.CustomerService;

@Controller
public class CustomerController {
    CustomerService customerService = new CustomerService();
	
	@RequestMapping("/CustomerRegistration") 
	 public String addForm(ModelMap model) {
		 model.addAttribute("User", new Customer());
		 return "CustomerRegistration";
	 }
	
	/*@RequestMapping(value="/register", method = RequestMethod.POST)  
	 public ModelAndView addCustomer(@ModelAttribute("user") User user) throws IOException, ServletException {  
        try {
        	customerService.getUser(user);
        	System.out.println(user);
       	 return new ModelAndView("acknowledgement","insertSuccess","User added successfully");
        } catch(Exception exc) {
       	 return new ModelAndView("acknowledgement", "insertFailure", "Sorry User can't added please try again");
        }
	 }*/
	@RequestMapping(value="/register", method = RequestMethod.POST)
    public String addAddress(@ModelAttribute("user") Customer user, ModelMap message) {  
		try {
			customerService.getUser(user);
		} catch (Exception e) {
    		message.addAttribute("message", "ENTER VALID DATA ONLY"); 
        }
		return "BranchIndex";
    }
	
	@RequestMapping(value="/getAllCustomer")  
    public ModelAndView getAllEmployee() {
        try {
        	return new ModelAndView ("RetrieveAllCustomer", "customers", customerService.getAllCustomer()); 
        } catch (DataBaseException e) {
        	return new ModelAndView ("RetrieveAllCustomer", "message",e.getMessage().toString());
        } 
    } 
}
