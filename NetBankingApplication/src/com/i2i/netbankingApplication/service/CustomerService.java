package com.i2i.netbankingApplication.service;

import java.util.List;

import com.i2i.netbankingApplication.Constand.Constant;
import com.i2i.netbankingApplication.dao.CustomerDao;
import com.i2i.netbankingApplication.exception.CustomerDataException;
import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.model.Account;
import com.i2i.netbankingApplication.model.Address;
import com.i2i.netbankingApplication.model.Customer;
import com.i2i.netbankingApplication.model.Role;
import com.i2i.netbankingApplication.model.UserRole;
import com.i2i.netbankingApplication.util.StringUtil;

/**
 * <p>
 *     When request comes from customerController. customer service performs add or fetch or fetchAll customer with model(customer),
 *     DAO(customer) and return the responses to customerController.
 *     CustomerService operate passing value's to CustomerDao based on requset's from CustomerController.
 *     It handles the customerDataException.
 *     It service validate the business logics using ConstantVariableUtil class.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-03.
 *
 */
public class CustomerService {
    private CustomerDao customerDao = new CustomerDao();
    
    /**
     * <p> 
     *     Get the customer object from customerController and pass to addCustomer method in customerDao.
     *     Calculate the customer age from customer DOB calculateAge method in StringUtil Class.
     *     To verify the customer attributes.
     *     It method validate the business logics using ConstantVariableUtil class.
     * </p>
     * 
     * @param customerAge
     *     Age of customer to be add.
     * @param customer
     *     Object of customer model class. It object used for add Customer.
     * 
     * @throws customerDataException
     *     If there is an error in the customer attribute exception is handle by customerDataException.
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application.
     */
    public void addCustomer(Customer customer) throws DataBaseException, CustomerDataException {
    	String accountNumer = customer.getAccountNumber();
    	Account account = customerDao.retrieveAccountByNumber(accountNumer);
    	if (null == account) {
    		throw new CustomerDataException("YOUR ACCOUNT NUMBER IS NOT VALID");  
    	}
    	if (null != account.getCustomer()) {
    		throw new CustomerDataException("YOUR ALREADY REGISTER THE NETBANKING..."); 
    	}
    	
    	String customerName = customer.getName();
    	//check the Customer Name valid or not
    	if (!StringUtil.isAlphabetic(customerName)) {
    		throw new CustomerDataException("YOUR NAME" + customerName +
                    "IS NOT VALID");  
    	}
    	
    	//check the Customer Name valid length or not
    	if (customerName.length() > Constant.MAXNAMELENGTH) {
         	throw new CustomerDataException("YOUR NAME" + customerName +
                    "IS NOT VALID");  
        }
    	
    	String customerDOB = customer.getDob();
    	//verify the Customer DOB valid or not
        if (StringUtil.isValidFormat(customerDOB)) {
            throw new CustomerDataException("YOUR FORMAT" + customerDOB +
                "FORMAT MUST 1/05/2000.INSERT VALID DOB..!!");  
        }
        
        int customerAge = StringUtil.calculateAge(customerDOB);
        //check the customer age valid limit or not
        if (customerAge > Constant.MAXAGELIMIT) {
        	throw new CustomerDataException("YOUR AGE IS NOT VALID");  
        }
        String customerId = Constant.CUSTOMERID + String.valueOf(getLastCustomerId() + 1);
        customer.setCustomerId(customerId);
        customer.setAge(customerAge);
        customer.setPassWord(StringUtil.generatePassword());
        customer.setStatus("ACTIVE");
        customerDao.insertCustomer(customer.getAccountNumber(), customer);
        addUserRole(customerId, Constant.USERROLEID);
    }
    
    /**
     * <p>
     *     This method Calculate the last address id. 
     *     return to the last address Id.
     * </p>
     * 
     * @return id
     *     return to the last customer address Id.
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application.
     */
	public int getNewAddressId() throws DataBaseException {
    	int newAddressId = Constant.INITIALIZEVARAILABLEVALUE;
    	for (Address address : customerDao.retriveAllAddresses()) {
    		int lastAddressId = address.getAddressId();
    		if (newAddressId <= lastAddressId) {
    			newAddressId = lastAddressId + 1;
    		}
    	}
    	return newAddressId;
    }
    
	/**
	 * <p>
     *     This method Calculate the last customer id. 
     *     return to the last customer Id.
     * </p>
     * 
	 * @return id
	 *     return to the last customer id value.
	 *     
	 * @throws DataBaseException 
	 *     It handle all the custom exception in NetBanking Application.
	 */
    public int getLastCustomerId() throws DataBaseException {
    	int id = Constant.INITIALIZEVARAILABLEVALUE;
        for (Customer customer : customerDao.retriveAllCustomers()) {
    		String lastCustomerId = customer.getCustomerId();
    		int temp = Integer.parseInt(lastCustomerId.substring(6, lastCustomerId.length()));
            if (id <= temp) {
            	id = temp;
            }
    	}
   		return id;
   	}
    
    /**
     * Retrieves all customers from CustomerDao.
     * 
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application.
     *         
     * @return List
     *     Return list of customers.
     */
    public List<Customer> getAllCustomers() throws DataBaseException {
    	return customerDao.retriveAllCustomers();
	}
    
    /**
     * <p>
     *     This method get the customer address from Customer controller. 
     *     pass to customer address addAddress method in customerDao.
     * </p>
     * 
     * @param address
     *     Object of Address model class.It object used for add Customer Address.
     *     
     * @return message
     *     Return status message (Success or failure).
     * 
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application.
     * @throws CustomerDataException 
     *     It handle all the business logic exception in NetBanking Application.
     */
   	public String addAddress(Address address) throws CustomerDataException ,DataBaseException {
		String customerId = Constant.CUSTOMERID + String.valueOf(getLastCustomerId());
	    try {
	        customerDao.addAddress(customerId, new Address(getNewAddressId(), address.getStreet(),
	               address.getCountry(), address.getCity(), address.getState() ,address.getPincode()));
	        Customer customer = getCustomerById(customerId);
	        return ("CUSTOMER REGISTER SUCCESSFULLY :: YOUR ID :" + customerId + " PASSWORD :" + customer.getPassWord());
	    } catch (DataBaseException e) {
	        customerDao.deleteCustomeryId(customerId);
	        throw new CustomerDataException("YOUR REGISTERATION NOT COMPLETED .PLEASE TRY AGAIN");
	    }
    }
    
	/**
     * <p> 
     *     Get the customer Id from CustomerController.
     *     It is passed to retrieveCustomerById method in CustomerDao and 
     *     Returns customer object to CustomerController.
     * </p>
     * 
     * @param customerId 
     *     Id of Customer.
     * 
     * @return customerController
     *     Return to the object of Customer class. 
     * 
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application..
	 */
	public Customer getCustomerById(String customerId) throws DataBaseException {
        return customerDao.retrieveCustomerById(customerId); 
    }
	
	/**mvn jetty:run
	 * <p> 
     *     Get the address Id from customerController.
     *     It is passed to retrieveAddressById method in customerDao and 
     *     returns address object to customerController.
     * </p>
     * 
	 * @param addressId
	 *     Id of Address.
	 *     
	 * @return customerController
     *     Return to the object of Address class. 
     *     
	 * @throws DataBaseException
	 *     It handle all the custom exception in NetBanking Application..
	 */
	public Address getAddressById(int addressId) throws DataBaseException {
	    return customerDao.retrieveAddressById(addressId);
	}
    
	/**
	 * <p>
	 *    Get the customer Id and roleId from customerController.
     *    It is called to insertRole method in customerDao.
     * </p> 
	 * 
	 * @param customerId
	 *     id of Customer.
	 * @param roleId
	 *     id of ROle.
	 *     
	 * @throws DataBaseException
	 *     It handle all the custom exception in NetBanking Application..
	 */
	public void addUserRole(String customerId, String roleId) throws DataBaseException {
		Customer customer = customerDao.retrieveCustomerById(customerId);
		if (customer != null) {
			Role role = customerDao.retrieveRoleById(roleId);
			if (IfUserRoleExist(customerId, roleId)) {
			    customerDao.insertUserRole(new UserRole(getUserRoleId(), customer, role));
			} else {
				throw new DataBaseException("ALREADY CUSTOMER ASSINED SAME ROLE");
			}
		} else {
			throw new DataBaseException("PLEASE ENTER THE VALID USERID");
		}
	}    
	/**
	 * <p>
	 *     If request comes CustomerController, it will calling to retriveAllRole method in customerDao.
	 *     Return to the lists of role.
     * </p>
     * 
	 * @return CustomerController
     *     Return to the lists of role.
	 * 
	 * @throws DataBaseException
	 *     It handle all the custom exception in NetBanking Application..
	 */
	public List<Role>getAllRoles() throws DataBaseException {
		return customerDao.retriveAllRoles();
	}
    
	/**
	 * <p>
	 *     If role size zero return false otherwise true.
	 *     It method called to getAllRole method in CustomerService.
	 * </p>
	 * 
	 * @return boolean
	 *     If role size zero return false otherwise true.
	 * 
	 * @throws DataBaseException
	 *     It handle all the custom exception in NetBanking Application..
	 */
	public boolean isRoleAvailable() throws DataBaseException {
		return (getAllRoles().size() != 0);
	}
    
	/**
	 * <p>
	 *     This method calculate the last userRole Id.
	 *     Return to the last userRole id.
	 * </p>
	 * 
	 * @return newUserRoleId
	 *     Return the new User Role Id.
	 *  
	 * @throws DataBaseException
	 *     It handle all the custom exception in NetBanking Application.
	 */
	public int getUserRoleId() throws DataBaseException {
		int newUserRoleId = Constant.INITIALIZEVARAILABLEVALUE;
		for (UserRole userRole : customerDao.retriveAllUserRoles()) {
			 int lastUserRoleId = userRole.getId();
			 if (newUserRoleId <= lastUserRoleId) {
				 newUserRoleId = lastUserRoleId + 1;
			 }
		}
		return newUserRoleId;
    }
	
	/**
	 * <p>
	 *     This method check the user role already allocated or not
	 *     If user role already allocated return false otherwise true.
	 * </p>
	 * 
	 * @param customerId
	 *     customerId of Customer.
	 * @param roleId
	 *     roleId of Role.
	 *     
	 * @return boolean
	 *     If user role already allocated return false otherwise true.
	 *     
	 * @throws DataBaseException
	 *     It handle all the custom exception in NetBanking Application..
	 */
	public boolean IfUserRoleExist(String customerId, String roleId) throws DataBaseException {
		for (UserRole userRole : customerDao.retriveAllUserRoles()) {
			if ((userRole.getCustomer().getCustomerId().equals(customerId)) && (userRole.getRole().getRoleId().equals(roleId))) {
			    return false;
			}
		}
		return true;
	 }
    
	/**
     * Checks the if validate user using customerId and password.
     * 
     * @param customerId
     *      Id of customer to check validate user or not.
     * @param password
     *      Password of customer to check validate role or not.
     * 
     * @return boolean
     *      If user already exists return true otherwise false.
     * 
     * @throws DataBaseException
     *      It handle all the custom exception in NetBanking Application.
     */
	public boolean ifValidateUser(String customerId, String password) throws DataBaseException,ExceptionInInitializerError {
	    Customer customer = getCustomerById(customerId); 
	    if (customer == null) {
	    	return false;  
	    }
	    if(!(customer.getPassWord().equals(password))) {
	    	return false; 
	    }
	    return true;
	}
    
    /**
     * <p>
     *     Checks is the role of customer.
     *     If role is approver return true, otherwise return false.
     * </p>
     * 
     * @param customerId
     *     Id of customer to check role.
     * 
     * @return boolean
     *     If userRoleCount is 2 return true, otherwise return false.
     * 
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application.
     */
	public boolean checkIfRole(String customerId) throws DataBaseException, ExceptionInInitializerError {
		int userRoleCount = Constant.INITIALIZEVARAILABLEVALUE;
		for (UserRole userRole : customerDao.retriveAllUserRoles()) {
			if (userRole.getCustomer().getCustomerId().equals(customerId)) {
				userRoleCount++;
			}
		}
	    if (userRoleCount == 1) {
			return false;
		}
		return true;
	}
}
			 
