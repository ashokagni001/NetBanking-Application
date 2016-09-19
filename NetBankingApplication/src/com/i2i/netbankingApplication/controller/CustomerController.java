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

/**
 * <p>
 *     When request comes from JSP page. 
 *     Customer Controller performs add or delete or fetch or fetchAll customer with model(Customer),
 *     service(Customer) and return the responses to JSP page.
 *     It handles the CustomerDataException, DataBaseException.
 *     If exception occurs it will write what type of exception occurred.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-03
 */
@Controller
public class CustomerController {
    private CustomerService customerService = new CustomerService();
    
    /**
     * Return the JSP page that contains options for customer operation.
     * 
     * @return CustomerIndex
     *     Return to the CustomerIndex JSP page.
     */ 	
    @RequestMapping(value = "/CustomerIndex")
	public String customerIndex() {
		return "CustomerIndex";
	}
    
   /**
    * Return to the CustomerRegistration JSP page to add new Customer detail.
    * 
    * @return CustomerRegistration
    *      Return to the CustomerRegistration JSP page.
    */
	@RequestMapping("/CustomerRegistration") 
	public String customerRegistration(ModelMap model) {
		model.addAttribute("Customer", new Customer());
		return "CustomerRegistration";
	}
	
    /**
     * <p>
     *     Get the customer object from JSP page and pass to getCustomer method in CustomerService.
     *     Return to JSP page CustomerRegistration with status message(success or failure) Or Address object.
     * </p>
     * 
     * @param customer
     *     Customer of the bank whose details are added.
     * @param message
     *     Display message using add attribute.
     * 
     * @return CustomerRegistration
     *     Return to JSP page CustomerRegistration with status message(success or failure).
     * 
     * @throws CustomerDataException
     *     If there is an error in the Customer Attribute exception is handle by CustomerDataException.
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application.
     */
	@RequestMapping(value="/register", method = RequestMethod.POST)
    public String addCustomer(@ModelAttribute("Customer") Customer customer, ModelMap message) { 
		String URL = "CustomerRegistration";
		try {
			customerService.addCustomer(customer);
			message.addAttribute("Address", new Address());
            return URL = "AddAddress";
		} catch (CustomerDataException e) {
    		message.addAttribute("message", e.getMessage().toString()); 
        } catch (DataBaseException e) {
    		message.addAttribute("message", e.getMessage().toString()); 
        } finally {
		    return URL;
        }
    }
	
	/**
	 * <p>
     *     Get the address object from JSP page and pass to getAddress method in CustomerService.
     *     Return to JSP page CustomerRegistration with status message(success or failure) Or Address object.
     * </p>
     * 
	 * @param address
	 *     Address of the Customer whose details are added.
	 * @param message
	 *     Display message using add attribute.
	 *     
	 * @return CustomerRegistration
	 *     Return to JSP page CustomerRegistration with status message(success or failure).
	 *     
	 * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application.
	 */
	@RequestMapping(value="/customerAddress", method = RequestMethod.POST)
    public String addAddress(@ModelAttribute("Address") Address address, ModelMap message) {  
		try {
			message.addAttribute("message", customerService.addAddress(address));
		} catch (CustomerDataException e) {
    		message.addAttribute("message", e.getMessage()); 
		} catch (DataBaseException e) {
    		message.addAttribute("message", e.getMessage()); 
        } finally {
		    return "Information";
        }
    }
	
	/**
	 * <p>
     *    This Method call to getAllCustomer method in CustomerService.
     *     Return to the RetrieveAllCustomer JSP page with customer lists or status message(failure).
     * </p>
	 * 
	 * @return GetCustomer
	 *     Return to JSP page GetCustomer.
	 *     
	 * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application.
	 */
	@RequestMapping(value = "/GetCustomer")
	public String getCustomerById(ModelMap message) {
        try {
		    message.addAttribute("customers", customerService.getAllCustomers());
        } catch(DataBaseException e) {
        	message.addAttribute("message", e.getMessage()); 
        }
        return "RetrieveAllCustomer"; 
	}
	
    /**
	 * <p>
	 *     This Method call to getAllCustomer method in CustomerService.
     *     Return to the RetrieveAllCustomer JSP page with customer lists or status message(failure).
     * </p>
     * 
	 * @param customerId
	 *     Id of Customer entered by user to view the corresponding record.
	 *     
	 * @return GetCustomer
	 *     Return to the ReteriveAllCustomer JSP page with Customer lists or status message(failure).
	 */	
	@RequestMapping(value="/getCustomer", method = RequestMethod.GET)  
    public String viewBranchById(@RequestParam("customerId")String customerId, ModelMap message) {
        try {
        	if (customerId.equals("all") || customerId.equals("All") || customerId.equals("ALL")) {
        		message.addAttribute("customers", customerService.getAllCustomers());
        	} else {
        		Customer customer = customerService.getCustomerById(customerId);
        		if (customer != null) {
        			message.addAttribute("customer", customer);
        		} else {
        			message.addAttribute("message", "ENTER VALID CUSTOMER ID ONLY");
        		}
        	}
        } catch (DataBaseException e) {
        	message.addAttribute("message", e.getMessage());
        } finally {
        	return "RetrieveAllCustomer";
        }
    }
	
	/**
	 * <p>
	 *     This Method call to getAddressById method in CustomerService.
     *     Return to the RetrieveAllCustomer JSP page with customer lists or status message(failure).
     * </p>
     * 
	 * @param addressId
	 *     Id of address entered by user to view the corresponding record.
	 *     
	 * @return RetrieveAddressById
	 *     Return to the RetrieveAddressById JSP page with Customer address or status message(failure).
	 */
	@RequestMapping(value="/viewCustomerAddress", method = RequestMethod.GET)
    public ModelAndView viewAddressById(@RequestParam("addressId")int addressId, ModelMap message) {
    	try {                     
            return new ModelAndView ("RetrieveAddressById", "address", customerService.getAddressById(addressId)); 
    	} catch (DataBaseException e) {
    		return new ModelAndView ("CustomerIndex", "message", e.getMessage().toString());
        }
    }
	
	
	/**
	 * <p>
	 *     Return to the AddUserRole JSP page with Role(Model) Object.
	 *     This Method check the role available or not using isRoleAvailable in CustomerService.
	 *     This Method call to getAllRole method in CustomerService.
     *     Return to the getMiniStatementByCustomerId JSP page with Customer MiniStatement or status message(failure).
     * </p>
	 * 
	 * @param model
	 *     Display the get all customers.
	 * @param message 
	 *     Display message using add attribute.
	 *      
	 * @return AddUserRole
	 *     Return to the AddUserRole JSP page with Role Detail or status message(failure).
	 * 
	 * @throws DataBaseException
	 *     It handle all the custom exception in NetBanking Application..
	 */
	@RequestMapping(value = "/getAllRole")
	public String getAllRoles(ModelMap model, ModelMap message) {
		try {
		    if (customerService.isRoleAvailable()) {
		    	message.addAttribute("customers", customerService.getAllCustomers());
			    model.addAttribute("roles", customerService.getAllRoles());
		    } else {
			    model.addAttribute("message", "SORRY ROLE IS NOT PRESENT");
		    }
		} catch (DataBaseException e) {
	        message.addAttribute("message", e.getMessage());
	    } finally {
	    	return "AddUserRole";
		}
	}
    
	/**
	 * <p>
	 *     This method insert to the user role detail.
	 *     This Method call to getAllRole method in CustomerService.
	 *     Return to the AddUserRole JSP page with Role Detail  and message (Success) or status message(failure).
	 * </p>
	 * 
	 * @param customerId
	 *     Id of Customer to add new user role. 
	 * @param roleId
	 *     Id of Role assigned for user.
	 * @param message
	 *     Display message using add attribute.
	 *     
	 * @return AddUserRole
	 *     Return to the AddUserRole JSP page with Role Detail or status message(failure).
	 *     
	 * @throws DataBaseException
	 *     It handle all the custom exception in NetBanking Application.
	 */
	@RequestMapping(value = "/insertRole", method = RequestMethod.GET)
	public String insertUserRole(@RequestParam("customerId") String customerId, @RequestParam("role") String roleId,
			ModelMap message) {
		try {
			customerService.addUserRole(customerId, roleId);
			message.addAttribute("message", "INFORMATION SAVED SUCESSFULLY");
			message.addAttribute("customers", customerService.getAllCustomers());
			message.addAttribute("roles", customerService.getAllRoles());
		} catch (DataBaseException  e) {
			message.addAttribute("message", e.getMessage());
			message.addAttribute("roles", customerService.getAllRoles());
			message.addAttribute("customers", customerService.getAllCustomers());
		} finally {
		    return "AddUserRole";
		}
	}
}
