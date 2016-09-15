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
import com.i2i.netbankingApplication.util.FileUtil;

/**
 * <p>
 *     When request comes from JSP page. 
 *     Customer controller performs add or delete or fetch or fetchAll customer with model(Customer),
 *     service(Customer) and return the responses to JSP page.
 *     It handles the NumberFormatException, CustomerDataException, DataBaseException.
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
     * Return the JSP page that contains options for customer operation
     * 
     * @return CustomerIndex
     *     Return to the CustomerIndex JSP page.
     */ 	
    @RequestMapping(value = "/CustomerIndex")
	public String customer() {
		return "CustomerIndex";
	}
    
   /**
    * <p>
    *     It displays a form to input data, here "Customer" is a reserved attribute which is used
    *     to display object data(Customer) into form.
    * </p>
    * 
    * @param model
    *      Customer model return the customer object.
    * 
    * @return CustomerRegistration
    *      Return to the CustomerRegistration JSP page with model(Customer) object.
    */
	@RequestMapping("/CustomerRegistration") 
	public String addForm(ModelMap model) {
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
     *     Object of Customer model class.
     * 
     * @param message
     *     Display message using add attribute.
     * 
     * @return CustomerRegistration
     *     Return to JSP page CustomerRegistration with status message(success or failure).
     * 
     * @throws NumberFormatException
     *     If the data is very large.
     * 
     * @throws CustomerDataException
     *     If there is an error in the Customer Attribute exception is handle by CustomerDataException.
     * 
     * @throws DataBaseException
     *     If there is an error in the given data like BadElementException.
     */
	@RequestMapping(value="/register", method = RequestMethod.POST)
    public String addCustomer(@ModelAttribute("Customer") Customer customer, ModelMap message) {  
		try {
			customerService.getCustomer(customer);
			message.addAttribute("Address", new Address());
            return "AddAddress";
		} catch (CustomerDataException e) {
			FileUtil.exceptionCreateFile("NEW CUSTOMER CREATE AT TIME EXCEPTION OCCUR.." + e);
    		message.addAttribute("message", e.getMessage().toString()); 
        } catch (DataBaseException e) {
    		message.addAttribute("message", e.getMessage().toString()); 
        } 
		return "CustomerRegistration";
    }
	
	/**
	 * <p>
     *     Get the address object from JSP page and pass to getAddress method in CustomerService.
     *     Return to JSP page CustomerRegistration with status message(success or failure) Or Address object.
     * </p>
     * 
	 * @param address
	 *     Object of Address model class.
	 *     
	 * @param message
	 *     Display message using add attribute.
	 *     
	 * @return CustomerRegistration
	 *     Return to JSP page CustomerRegistration with status message(success or failure).
	 *     
	 * @throws DataBaseException
     *     If there is an error in the given data like BadElementException.
	 */
	@RequestMapping(value="/customerAddress", method = RequestMethod.POST)
    public String addAddress(@ModelAttribute("Address") Address address, ModelMap message) {  
		try {
			message.addAttribute("message", customerService.getAddress(address));
		} catch (DataBaseException e) {
			FileUtil.exceptionCreateFile("NEW CUSTOMER ADDTESS CREATE AT TIME EXCEPTION OCCUR.." + e);
    		message.addAttribute("message", e.getMessage()); 
        } finally {
		    return "login";
        }
    }
	
	/**
	 * <p>
     *     This Method call to getAllCustomer method in CustomerService.
     *     Return to the RetrieveAllCustomer JSP page with list of Customers or status message(failure).
     * </p>
     * 
	 * @return GetCustomer
	 *     Return to the RetrieveAllCustomer JSP page with list of Customers or status message(failure)..
	 *     
	 * @throws DataBaseException
     *     If there is an error in the given data like BadElementException.
	 */
	@RequestMapping(value = "/GetCustomer")
	public String getAllCustomer(ModelMap message) {
        try {
		    message.addAttribute("customers", customerService.getAllCustomer());
        } catch(DataBaseException e) {
        	FileUtil.exceptionCreateFile("VIEW ALL CUSTOMER AT TIME EXCEPTION OCCUR." + e);
        	message.addAttribute("message", e.getMessage()); 
        }
        return "RetrieveAllCustomer"; 
	}
	
    /**
	 * <p>
	 *     This Method call to getAllCustomer method in CustomerService.
	 *     This Method return list of Customers in specific branch only or specific customerId detail.
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
        	FileUtil.exceptionCreateFile("VIEW CUSTOMER BY BRANCH AT TIME EXCEPTION OCCUR. CUSTOMER ID-->" + 
                    customerId + e);
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
    		FileUtil.exceptionCreateFile("VIEW CUSTOMER ADDRESS AT TIME EXCEPTION OCCUR. ADDRESS ID-->" + 
    				addressId + e);
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
	 *     Customer model return the customer object.
	 *     
	 * @param message 
	 *     Display message using add attribute.
	 *     
	 * @return AddUserRole
	 *     Return to the AddUserRole JSP page with Role Detail or status message(failure).
	 * 
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 */
	@RequestMapping(value = "/getAllRole")
	public String getAllRole(ModelMap model, ModelMap message) {
		try {
		    if (customerService.isRoleAvailable()) {
			    model.addAttribute("roles", customerService.getAllRole());
		    } else {
			    model.addAttribute("message", "SORRY ROLE IS NOT PRESENT");
		    }
		} catch (DataBaseException e) {
			FileUtil.exceptionCreateFile("GET ALL ROLE AT TIME EXCEPTION OCCUR.." + e);
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
	 *     Id of Customer entered by user to Create new User role.. 
	 *     
	 * @param roleId
	 *     Id of Role.
	 *     
	 * @param message
	 *     Display message using add attribute.
	 *     
	 * @return AddUserRole
	 *     Return to the AddUserRole JSP page with Role Detail or status message(failure).
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 */
	@RequestMapping(value = "/insertRole", method = RequestMethod.GET)
	public String insertUserRole(@RequestParam("customerId") String customerId, @RequestParam("role") String roleId,
			ModelMap message) {
		try {
			customerService.insertUserRole(customerId, roleId);
			message.addAttribute("message", "INFORMATION SAVED SUCESSFULLY");
			message.addAttribute("roles", customerService.getAllRole());
		} catch (DataBaseException  e) {
			FileUtil.exceptionCreateFile("INSERT NEW USER ROLE AT TIME EXCEPTION OCCUR.." +
		            "CUSTOMER ID : " + customerId + " ROLE ID : " + roleId  + e);
			message.addAttribute("message", e.getMessage().toString());
			message.addAttribute("roles", customerService.getAllRole());
		} finally {
		    return "AddUserRole";
		}
	}
}
