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
			message.addAttribute("message", customerService.getAddress(address));
            return "login";
		} catch (DataBaseException e) {
    		message.addAttribute("message", "ENTER VALID DATA ONLY"); 
        }
		return "login";
    }
	
	@RequestMapping(value = "/GetCustomer")
	public String getCustomerById( ModelMap message) throws DataBaseException {
		message.addAttribute("customers", customerService.getAllCustomer());
		return "GetCustomer";
	}
	
	@RequestMapping(value="/getCustomer", method = RequestMethod.GET)  
    public String viewBranchById (@RequestParam("customerId")String customerId, ModelMap message) {
        try {
        	if (customerId.equals("all") || customerId.equals("All") || customerId.equals("ALL")) {
        		message.addAttribute("customers", customerService.getAllCustomer());
        	} else {
        		Customer customer = customerService.getCustomerById(customerId);
        		if (customer != null) {
        			message.addAttribute("customer", customer);
        		} else {
        			message.addAttribute("message", "ENTER VALID CUSTOMER ID ONLY");
        		}
        	}
        } catch (DataBaseException e) {
        	message.addAttribute("message", e.getMessage().toString());
        } finally {
        	return "GetCustomer";
        }
    }
	
	@RequestMapping(value="/viewCustomerAddress", method = RequestMethod.GET)
    public ModelAndView viewAddressById(@RequestParam("addressId")int addressId, ModelMap message) {
    	try {                     
            return new ModelAndView ("RetrieveAddressById", "address", customerService.getAddressById(addressId)); 
    	} catch (DataBaseException e) {
    		return new ModelAndView ("CustomerIndex", "message", e.getMessage().toString());
        }
    }
	
	@RequestMapping(value = "/getMiniStatementByCustomerId")
	public String getMiniStatementByCustomerId() {
		return "GetMiniStatementByCustomerId";
	}
	
	@RequestMapping(value="/viewMiniStatementByCustomerId", method = RequestMethod.GET)  
    public ModelAndView viewMiniStatementByCustomerId (@RequestParam("customerId")String customerId, ModelMap message) {
        try {
            return new ModelAndView("RetrieveMiniStatementByCustomerId", "miniStatement", customerService.getMiniStatementByCustomerId(customerId));
        } catch (DataBaseException e) {
        	return new ModelAndView("CustomerIndex", "message", "ENTER VALID CUSTOMER ID ONLY");
        }
    }
	
	@RequestMapping(value = "/addUserRole")
	public String addUserRole(ModelMap model) throws DataBaseException {
		if (customerService.isRoleAvailable()) {
			model.addAttribute("roles", customerService.getAllRole());
		} else {
			model.addAttribute("message", "Sorry role is not present");
		}
		return "AddUserRole";
	}

	@RequestMapping(value = "/insertRole", method = RequestMethod.GET)
	public String addUserRole(@RequestParam("customerId") String customerId, @RequestParam("role") String roleId,
			ModelMap message) throws DataBaseException {
		try {
			customerService.insertRole(customerId, roleId);
			message.addAttribute("message", "INFORMATION SAVED SUCESSFULLY");
		} catch (DataBaseException  exp) {
			message.addAttribute("message", "SORRY INFORMATION CAN NOT SAVED PLEASE TRY AGAIN" + exp);
			message.addAttribute("roles", customerService.getAllRole());
		}
		return "AddUserRole";
	}

}
