package com.i2i.netbankingApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.i2i.netbankingApplication.exception.CustomerDataException;
import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.model.Address;
import com.i2i.netbankingApplication.model.Customer;
import com.i2i.netbankingApplication.service.CustomerService;

@Controller
public class CustomerController {
    CustomerService customerService = new CustomerService();
	
    @RequestMapping(value = "/CustomerIndex")
	public String customer() {
		return "CustomerIndex";
	}
    
	@RequestMapping("/CustomerRegistration") 
	public String addForm(ModelMap model) {
		model.addAttribute("Customer", new Customer());
		return "CustomerRegistration";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
    public String addAddress(@ModelAttribute("Customer") Customer user, ModelMap message) {  
		try {
			customerService.getUser(user);
			message.addAttribute("Address", new Address());
            return "AddAddress";
		} catch (CustomerDataException e) {
    		message.addAttribute("message", e.getMessage().toString()); 
        } catch (DataBaseException e) {
    		message.addAttribute("message", e.getMessage().toString()); 
        } 
		return "CustomerRegistration";
    }
	
	@RequestMapping(value="/customerAddress", method = RequestMethod.POST)
    public String addAddress(@ModelAttribute("Address") Address address, ModelMap message) {  
		try {
            customerService.getAddress(address);
            return "BranchIndex";
		} catch (DataBaseException e) {
    		message.addAttribute("message", "ENTER VALID DATA ONLY"); 
        }
		return "CustomerRegistration";
    }
	
	@RequestMapping(value = "/GetCustomer")
	public String getCustomerById() {
		return "GetCustomer";
	}
	
	@RequestMapping(value="/getCustomer", method = RequestMethod.GET)  
    public ModelAndView viewBranchById (@RequestParam("customerId")String customerId, ModelMap message) {
        try {
        	if (customerId.equals("all") || customerId.equals("All") || customerId.equals("ALL")) {
        		return new ModelAndView ("GetCustomer", "customers", customerService.getAllCustomer());
        	} else {
        		Customer customer = customerService.getCustomerById(customerId);
        		if (customer != null) {
        			return new ModelAndView("GetCustomer", "customer", customer);
        		} else {
        			return new ModelAndView("GetCustomer", "message", "ENTER VALID CUSTOMER ID ONLY");
        		}
        	}
        } catch (DataBaseException e) {
        	return new ModelAndView("GetCustomer", "message", e.getMessage().toString());
        }
    }
	
	@RequestMapping(value="/viewCustomerAddress", method = RequestMethod.GET)
    public ModelAndView viewAddressById(@RequestParam("addressId")int addressId, ModelMap message) {
    	try {                     
            return new ModelAndView ("RetrieveAddressById", "address", customerService.getAddressById(addressId)); 
    	} catch (DataBaseException e) {
    		return new ModelAndView ("RetrieveAddressById", "message", e.getMessage().toString());
        }
	}
}
