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
 * When request comes from JSP page. 
 * Customer controller performs add or delete or fetch or fetchAll customer with model(Customer),
 * service(Customer) and return the responses to JSP page.
 * It handles the NumberFormatException, CustomerDataException, DataBaseException.
 * If exception occurs it will write what type of exception occurred.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-03
 */
@Controller
public class CustomerController {
    CustomerService customerService = new CustomerService();
	
    /**
     * Return the JSP page that contains options for customer operation
     * 
     * @return CustomerIndex
     *     Return to the CustomerIndex JSP page.
     */
    @RequestMapping(value = "/CustomerIndex")
	public String customerIndex() {
		return "CustomerIndex";
	}
    
    /**
     * 
     * @return index
     *     Return to the index JSP page.
     */
    @RequestMapping(value = "/index")
	public String login() {
		return "index";
	}
    
   /**
    * <p>
    *    It displays a form to input data, here "Customer" is a reserved attribute which is used
    *    to display object data(Customer) into form.
    * </p>
    * 
    * @param model
    *        Customer model return the customer object.
    * 
    * @return CustomerRegistration
    *         Return to the CustomerRegistration JSP page with model(Customer) object.
    */
	@RequestMapping("/CustomerRegistration") 
	public String customerRegistration(ModelMap model) {
		model.addAttribute("Customer", new Customer());
		return "CustomerRegistration";
	}
	
	/**
     * <p>
     *    Get the customer object from JSP page and pass to getCustomer method in CustomerService.
     *    Return to JSP page CustomerRegistration with status message(success or failure) Or Address object.
     * </p>
     * 
     * @param customer
     *        Object of Customer model class.
     * 
     * @param message
     *        Display message using add attribute.
     * 
     * @return CustomerRegistration
     *         Return to JSP page CustomerRegistration with status message(success or failure).
     * 
     * @throws NumberFormatException
     *         If the data is very large.
     * 
     * @throws CustomerDataException
     *         If there is an error in the Customer Attribute exception is handle by CustomerDataException.
     * 
     * @throws DataBaseException
     *         If there is an error in the given data like BadElementException.
     */
	@RequestMapping(value="/register", method = RequestMethod.POST)
    public String getCustomer(@ModelAttribute("Customer") Customer customer, ModelMap message) {  
		try {
			customerService.getCustomer(customer);
			message.addAttribute("Address", new Address());
            return "AddAddress";
		} catch (NumberFormatException e) {
			FileUtil.exceptionOccurCreateFile("CUSTOMER ADD AT TIME EXCEPTION OCCUR..\nDATAS-->" + customer  + e);
			message.addAttribute("message", "YOUR DATAS IS VERY LARGE.. ENTER VALID DATA"); 
		} catch (CustomerDataException e) {
			FileUtil.exceptionOccurCreateFile("CUSTOMER ADD AT TIME BUSINESS LOGIC EXCEPTION OCCUR..\nDATAS-->" + customer  + e);
    		message.addAttribute("message", e.getMessage().toString()); 
        } catch (DataBaseException e) {
        	FileUtil.exceptionOccurCreateFile("CUSTOMER ADD AT TIME EXCEPTION OCCUR ..\nDATAS-->" + customer  + e);
    		message.addAttribute("message", e.getMessage().toString()); 
        } 
		return "CustomerRegistration";
    }
	
	/**
	 * <p>
     *    Get the address object from JSP page and pass to getAddress method in CustomerService.
     *    Return to JSP page CustomerRegistration with status message(success or failure) Or Address object.
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
    public String getAddress(@ModelAttribute("Address") Address address, ModelMap message) {  
		try {
			message.addAttribute("message", customerService.getAddress(address));
            return "CustomerIndex";
		} catch (DataBaseException e) {
			FileUtil.exceptionOccurCreateFile("CUSTOMER ADDRESS AT TIME EXCEPTION OCCUR ..\nDATAS-->" + address  + e);
    		message.addAttribute("message", "ENTER VALID DATA ONLY"); 
        }
		return "CustomerRegistration";
    }
	
	/**
	 * <p>
     *    It displays a form to input data, here "Customer" is a reserved attribute which is used
     *    to display object data(Customer) into form.
     * </p>
	 * 
	 * @return GetCustomer
	 *     Return to JSP page GetCustomer.
	 * @throws DataBaseException
     *     If there is an error in the given data like BadElementException.
	 */
	@RequestMapping(value = "/GetCustomer")
	public String getCustomer (ModelMap message) throws DataBaseException {
		message.addAttribute("customers", customerService.getAllCustomer());
		return "GetCustomer";
	}
	
	/**
	 * <p>
	 *    This Method call to getAllCustomer method in CustomerService.
     *    Return to the RetrieveAllCustomer JSP page with customer lists or status message(failure).
     * </p>
	 * @param customerId
	 *     Id of Customer entered by user to view the corresponding record.
	 *     
	 * @return GetCustomer
	 *     Return to the ReteriveAllCustomer JSP page with Customer lists or status message(failure).
	 */
	@RequestMapping(value="/getCustomer", method = RequestMethod.GET)  
<<<<<<< HEAD
    public ModelAndView getCustomerById (@RequestParam("customerId")String customerId) {
=======
    public String viewBranchById (@RequestParam("customerId")String customerId, ModelMap message) {
>>>>>>> 464c0d73467590002b690837ff5aaebeebc73592
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
<<<<<<< HEAD
        	FileUtil.exceptionOccurCreateFile("CUSTOMER VIEW AT TIME EXCEPTION OCCUR ..\nDATAS-->" + customerId  + e);
        	return new ModelAndView("GetCustomer", "message", e.getMessage().toString());
=======
        	message.addAttribute("message", e.getMessage().toString());
        } finally {
        	return "GetCustomer";
>>>>>>> 464c0d73467590002b690837ff5aaebeebc73592
        }
    }
	
	/**
	 * <p>
	 *    This Method call to getAddressById method in CustomerService.
     *    Return to the RetrieveAllCustomer JSP page with customer lists or status message(failure).
     * </p>
     * 
	 * @param addressId
	 *     Id of address entered by user to view the corresponding record.
	 *     
	 * @return RetrieveAddressById
	 *     Return to the RetrieveAddressById JSP page with Customer address or status message(failure).
	 */
	@RequestMapping(value="/viewCustomerAddress", method = RequestMethod.GET)
    public ModelAndView getAddressById(@RequestParam("addressId")int addressId) {
    	try {                     
            return new ModelAndView ("RetrieveAddressById", "address", customerService.getAddressById(addressId)); 
    	} catch (DataBaseException e) {
<<<<<<< HEAD
    		FileUtil.exceptionOccurCreateFile("CUSTOMER VIEW AT TIME EXCEPTION OCCUR ..\nDATAS-->" + addressId  + e);
    		return new ModelAndView ("RetrieveAddressById", "message", e.getMessage().toString());
=======
    		return new ModelAndView ("CustomerIndex", "message", e.getMessage().toString());
>>>>>>> 75919ca2c5d299fe30bbf9316c2e30ef0c5a3563
        }
    }
	
	/**
	 * It return to the  GetMiniStatementByCustomerId JSP page.
	 * It form used for enter customerId to view miniStatement.
	 *  
	 * @return GetMiniStatementByCustomerId
	 *     Return to the GetMiniStatementByCustomerId JSP page.
	 */   
	@RequestMapping(value = "/getMiniStatementByCustomerId")
	public String getMiniStatementByCustomerId() {
		return "GetMiniStatementByCustomerId";
	}
	/**
	 * <p>
	 *    Get the Customer Id from JSP page.
	 *    This Method call to getMiniStatementByCustomerId method in CustomerService.
     *    Return to the getMiniStatementByCustomerId JSP page with Customer MiniStatement or status message(failure).
     * </p>
     * 
	 * @param customerId
	 *     Id of Customer entered by user to view the corresponding record(MiniStatement).
	 *     
	 * @return RetrieveMiniStatementByCustomerId
	 *      Return to the RetrieveMiniStatementByCustomerId JSP page with Customer MiniStatement or status message(failure).
	 * 
	 */
	@RequestMapping(value="/viewMiniStatementByCustomerId", method = RequestMethod.GET)  
    public ModelAndView getMiniStatementByCustomerId (@RequestParam("customerId")String customerId) {
        try {
            return new ModelAndView("RetrieveMiniStatementByCustomerId", "miniStatement", customerService.getMiniStatementByCustomerId(customerId));
        } catch (DataBaseException e) {
<<<<<<< HEAD
    		FileUtil.exceptionOccurCreateFile("CUSTOMER VIEW AT TIME EXCEPTION OCCUR ..\nDATAS-->" + customerId  + e);
        	return new ModelAndView("RetrieveMiniStatementByCustomerId", "message", "ENTER VALID CUSTOMER ID ONLY");
=======
        	return new ModelAndView("CustomerIndex", "message", "ENTER VALID CUSTOMER ID ONLY");
>>>>>>> 75919ca2c5d299fe30bbf9316c2e30ef0c5a3563
        }
    }
<<<<<<< HEAD
=======
	
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
			message.addAttribute("roles", customerService.getAllRole());
		} catch (DataBaseException  e) {
			message.addAttribute("message", e.getMessage().toString());
			message.addAttribute("roles", customerService.getAllRole());
		}
		return "AddUserRole";
	}

>>>>>>> 464c0d73467590002b690837ff5aaebeebc73592
}
