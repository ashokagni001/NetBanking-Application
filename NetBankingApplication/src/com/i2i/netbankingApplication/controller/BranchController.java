package com.i2i.netbankingApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.model.Address;
import com.i2i.netbankingApplication.model.Branch;
import com.i2i.netbankingApplication.service.BranchService;

/**
 * <p>
 *     When request comes from JSP page. 
 *     Branch controller performs add or delete or fetch or fetchAll with model(Branch),
 *     service(Branch) and return the responses to JSP page.
 *     It handles the DataBaseException.
 *     If exception occurs it will write what type of exception occurred.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-03.
 */
@Controller
public class BranchController {
	private BranchService branchService = new BranchService();
	
   /**
    * If request come this method return the JSP page that contains options for Branch operation
    * 
    * @return BranchIndex
    *     Return to the BranchIndex JSP page.
    */
	@RequestMapping(value = "/BranchIndex")
	public String login() {
		return "BranchIndex";
	}
	
	/**
	 *  If request come this method return to the AddBranch JSP page use to add the new branch.
	 * 
	 * @return AddBranch
	 *     Return to the AddBranch JSP page.
	 */
	@RequestMapping(value = "/addBranch")
	public String getBranch() {
		return "AddBranch";
	}
	
	/**
     * <p>
     *     Get the Branch emailId from JSP page and pass to getBranch method in BranchService.
     *     Return to JSP page BranchIndex with status message(success or failure) Or Address object.
     * </p>
     * 
     * @param emailId
     *     emailId of entered by user to Add the Branch.
     * 
     * @param message
     *     Display message using add attribute.
     *     
     * @return AddAddress
     *     Return to JSP page AddAddress with Address Object.
     *     
     * @return BranchIndex
     *     Return to JSP page BranchIndex with Status message(failure).
     * 
     * @throws DataBaseException
     *     If there is an error in the given data like BadElementException.
     */
	@RequestMapping(value="/insertBranch", method = RequestMethod.POST)
    public String addBranch(@RequestParam("emailId") String emailId, ModelMap message) { 
		String URL = "AddAddress";
		try {
			if (emailId != null) {
			    branchService.getBranch(emailId);
			    message.addAttribute("BranchAddress", new Address());
			} else {
				message.addAttribute("message", " PLEASE FILL THE FORM ");
			}
	    } catch (DataBaseException e) {
    		message.addAttribute("message", e.getMessage()); 
    		URL = "BranchIndex";
        }
		return URL; 
    }
	
	
	/**
	 * <p>
     *     Get the address object from JSP page and pass to getAddress method in BranchService.
     *     Return to JSP page BranchIndex with Status message (Success with IFSC code Or failure).
     * </p>
     * 
	 * @param address
	 *     Object of Address model class.
	 *     
	 * @param message
	 *     Display message using add attribute.
	 *     
	 * @return BranchIndex
	 *     Return to JSP page BranchIndex with status message(Success or Failure).
	 *     
	 * @throws DataBaseException
     *     If there is an error in the given data like BadElementException.
	 */
	@RequestMapping(value="/address", method = RequestMethod.POST)
    public String addAddress(@ModelAttribute("BranchAddress") Address address, ModelMap message) {  
		try {
			message.addAttribute("message","BRANCH ADDED SUCCESS. BRANCH IFSC IS :: " + branchService.getAddress(address));
	    } catch (DataBaseException e) {
    		message.addAttribute("message", e.getMessage()); 
        } finally {
        	return "BranchIndex";
        }
    }
	
	/**
	 * <p>
	 *     This method delete the corresponding record(Branch IFSC).
	 *     This Method call to deleteBranchById method in BranchService.
     *     Return to the GetBranch JSP page with list of Branches or status message(failure).
     * </p>
     * 
	 * @param BranchId
	 *     Id of Branch entered by user to delete the corresponding record.
	 *     
	 * @return RetrieveAllBranch
	 *     Return to the GetBranch JSP page with list of Branches or status message(failure).
	 *     
	 * @throws DataBaseException
     *     If there is an error in the given data like BadElementException.
	 */
	@RequestMapping(value="/deleteBranchById", method = RequestMethod.GET)
    public String deleteBranch(@RequestParam("ifsc")String ifsc, ModelMap message) {
    	try {       
            branchService.deleteBranchById(ifsc);
            message.addAttribute("message", "BRANCH DELETED SUCESSFULLY");
            message.addAttribute("branches", branchService.getAllBranch());
	    } catch (DataBaseException e) {
    		message.addAttribute("message", e.getMessage()); 
    		message.addAttribute("branches", branchService.getAllBranch());
        } finally {
        	return "RetrieveAllBranch";
        }
    }
	
	
	/**
	 * <p>
     *     This Method call to getAllBranch method in BranchService.
     *     Return to the RetrieveAllBranch JSP page with list of Branches.
	 * </p>
	 * 
	 * @return RetrieveAllBranch
	 *     Return to the RetrieveAllBranch JSP page with list of Branches.
	 *     
	 * @throws DataBaseException 
	 *     If there is an error in the given data like BadElementException.
	 */ 
	@RequestMapping(value = "/GetBranch")
	public String getAllBranch(ModelMap message) throws DataBaseException {
		message.addAttribute("branches", branchService.getAllBranch());
		return "RetrieveAllBranch";
	}
	/**
	 * <p>
	 *     This Method call to getAllBranch method in BranchService.
     *     Return to the RetrieveAllBranch JSP page with list of Branches or status message(failure).
     * </p>
     * 
	 * @param BranchId
	 *     Id of Branch entered by user to view the corresponding record.
	 *     
	 * @return GetBranch
	 *     Return to the ReteriveAllBranch JSP page with list of Branches or status message(failure).
	 *  
	 * @throws DataBaseException
     *     If there is an error in the given data like BadElementException.
	 */
	@RequestMapping(value="/getBranch", method = RequestMethod.GET)  
    public ModelAndView viewBranchById (@RequestParam("ifsc")String ifsc) {
        try {
        	if (ifsc.equals("all") || ifsc.equals("All") || ifsc.equals("ALL")) {
        		return new ModelAndView ("RetrieveAllBranch", "branches", branchService.getAllBranch());
        	} else  {
        		Branch branch = branchService.getBranchById(ifsc);
        		if (branch != null) {
                    return new ModelAndView("RetrieveAllBranch","branch", branch);
        		} else {
        			return new ModelAndView("RetrieveAllBranch","message", "ENTER VALID IFSC ONLY");
        		}
        	}
        } catch (DataBaseException e) {
        	return new ModelAndView("BranchIndex","message",  e.getMessage());
        }
	}
	
	/**
	 * <p>
	 *     This Method call to getAddressById method in BranchService.
     *     Return to the RetrieveAddressById JSP page with Branch Address or status message(failure).
     * </p>
     * 
	 * @param addressId
	 *     Id of address entered by user to view the corresponding record.
	 *     
	 * @return RetrieveAddressById
	 *     Return to the RetrieveAddressById JSP page with Branch address or status message(failure).
	 *     
	 * @throws DataBaseException
     *     If there is an error in the given data like BadElementException.
	 */
	@RequestMapping(value="/viewBranchAddress", method = RequestMethod.GET)
    public ModelAndView viewAddressById(@RequestParam("addressId")int addressId, ModelMap message) {
    	try {                     
            return new ModelAndView ("RetrieveAddressById", "address", branchService.getAddressById(addressId)); 
    	} catch (DataBaseException e) {
    		return new ModelAndView ("RetrieveAddressById", "message", e.getMessage());
        }
	}
	
	/**
     * Return to JSP page AddAccount.It used new add Customer account.
	 * 
	 * @return GetBranch
	 *     Return to JSP page AddAccount.
	 */
	@RequestMapping(value = "/AddAccount")
	public String getAccount() {
		return "AddAccount";
	}
	
	/**
	 * <p>
	 *     This Method call to getAccount method in BranchService.
     *     Return to the BranchIndex JSP page with success or status message(failure).
     * </p>
     * 
	 * @param accountNumber
	 *     accountNumber of Account.
	 * @param balance
	 *     balance of Account.
	 * @param accounttype
	 *     accountTypre of Account.
	 * @param ifsc
	 *     ifsc of Branch. 
	 * @param message
	 *     Display message using add attribute.
	 *     
	 * @return RetrieveAddressById
	 *     Return to the RetrieveAddressById JSP page with Branch address or status message(failure).
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="/addAccount", method = RequestMethod.POST)  
    public String getAccount(@RequestParam("accountNumber")String accountNumber, @RequestParam("balance")String balance, 
    		@RequestParam("accounttype")String accounttype, @RequestParam("ifscode")String ifsc, ModelMap message) throws  DataBaseException {
		try { 
			message.addAttribute("message", branchService.getAccount(accountNumber, Double.parseDouble(balance), accounttype, ifsc));
		} catch(DataBaseException e) {
			message.addAttribute("message", e.getMessage());
		} finally {
	     	return "BranchIndex";
		}
	}
	
	/**
	 * It return to the ViewAccountByBranch JSP page and it form used for enter BranchId to view account detail.
	 *  
	 * @return ViewAccountByBranch
	 *     Return to the ViewAccountByBranch JSP page.
	 */
	@RequestMapping(value = "/ViewAccountByBranch")
	public String viewAccountByBranch() {
		return "ViewAccountByBranch";
	}
	
	
	/**
	 * <p>
	 *     This Method call to viewAccountByBranch method in BranchService.
     *     Return to the BranchIndex JSP page with list of accounts or status message(failure).
     * </p> 
	 * @param ifsc
	 *     ifsc of Branch. It use to retrieve all account detail in One Branch.
	 *      
	 * @param message
	 *     Display message using add attribute.
	 *     
	 * @throws DataBaseException
     *     If there is an error in the given data like BadElementException.
     *     
     * @return ViewAccountByBranch
	 *     Return to the ViewAccountByBranch JSP page with list of accounts or status message(failure).
	 */
	@RequestMapping(value="/getAccount", method = RequestMethod.GET)  
    public String viewAccountByBranch (@RequestParam("ifsc")String ifsc, ModelMap message) {
		try { 
			message.addAttribute("accounts", branchService.viewAccountByBranch(ifsc));
		} catch(DataBaseException e) {
			message.addAttribute("message", e.getMessage().toString());
		} finally {
	     	return "ViewAccountByBranch";
		}
	}	
}
