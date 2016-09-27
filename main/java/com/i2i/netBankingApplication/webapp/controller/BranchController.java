package com.i2i.netBankingApplication.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.i2i.netBankingApplication.Constants;
import com.i2i.netBankingApplication.exception.BranchDataException;
import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.model.Account;
import com.i2i.netBankingApplication.model.Branch;
import com.i2i.netBankingApplication.service.BranchManager;

@Controller
public class BranchController {
    
    @Autowired
    private BranchManager branchManager;
    /**
     * If request come this method return to the AddBranch JSP page use to add the new branch.
     * 
     * @return AddBranch
     *     Return to the AddBranch JSP page.
     */
    @RequestMapping(value = "/addBranch")
    public String addBranch(ModelMap message) {
        message.addAttribute("Branch", new Branch());
        return "addBranch";
    }
    
    /**
     * <p>
     *     Get the Branch emailId from JSP page and pass to addBranch method in BranchService.
     *     Return to JSP page BranchIndex with status message(success or failure) or Address object.
     * </p>
     * 
     * @param emailId
     *     emailId of entered by user to Add the Branch.
     * @param message
     *     Display message using add attribute.
     *     
     * @return AddAddress
     *     Return to JSP page AddAddress with Address Object.
     * @return BranchIndex
     *     Return to JSP page BranchIndex with Status message(failure).
     * 
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking application.
     */
    @RequestMapping(value="/insertBranch", method = RequestMethod.POST)
    public String addBranch(@ModelAttribute("Branch") Branch branch, ModelMap message) { 
        try {
        	message.addAttribute("message", branchManager.addBranch(branch));
        } catch (DataBaseException e) {
            message.addAttribute("message", e.getMessage()); 
        } finally {
            return "addBranch";
        }
    }
    
    /**
     * <p>
     *     This Method call to getAllBranch method in branchManager.
     *     Return to the RetrieveAllBranch JSP page with list of Branches.
     * </p>
     * 
     * @return RetrieveAllBranch
     *     Return to the RetrieveAllBranch JSP page with list of Branches.
     *     
     * @throws DataBaseException 
     *     It handle all the custom exception in NetBanking application.
     */ 
    @RequestMapping(value = "/viewBranches")
    public String getAllBranches(ModelMap message) throws DataBaseException {
        message.addAttribute("branches", branchManager.getBranches());
        return "retrieveBranches";
    }
    
    /**
     * <p>
     *     This Method call to getAllBranch method in branchManager.
     *     Return to the RetrieveAllBranch JSP page with list of Branches or status message(failure).
     * </p>
     * 
     * @param BranchId
     *     Id of Branch entered by user to view the corresponding record.
     *     
     * @return RetrieveAllBranch
     *     Return to the ReteriveAllBranch JSP page with list of Branches or status message(failure).
     *  
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking application.
     */
    @RequestMapping(value="/getBranchByIFSCode", method = RequestMethod.GET)  
    public String getBranchByIFSCode(@RequestParam("IFSCode")String IFSCode, ModelMap message) {
        try {
            if (IFSCode.equals("all") || IFSCode.equals("All") || IFSCode.equals("ALL")) {
                message.addAttribute("branches", branchManager.getBranches());
            } else  {
                Branch branch = branchManager.getBranchByIFSCode(IFSCode);
                if (branch != null) {
                    message.addAttribute("branch", branch);
                } else {
                    message.addAttribute("message", "ENTER VALID IFSCode ONLY");
                    message.addAttribute("branches", branchManager.getBranches());
                }
            }
        } catch (DataBaseException e) {
            message.addAttribute("message",  e.getMessage());
            message.addAttribute("branches", branchManager.getBranches());
        } finally {
            return "retrieveBranches";
        }
    }
    
    /**
     * Return to JSP page AddAccount.It used new add Customer account.
     * @param message 
	 * 
	 * @return AddAccount
	 *     Return to JSP page AddAccount.
	 * @throws DataBaseException 
	 *     It handle all the custom exception in NetBanking application.
	 */
	@RequestMapping(value = "/addAccount")
	public String addAccountForm(ModelMap message) throws DataBaseException {
		message.addAttribute("branches", branchManager.getBranches());
		return "addAccount";
	}
	
	/**
	 * <p>
	 *     This Method call to getAccount method in BranchService.
     *     Return to the BranchIndex JSP page with success or status message(failure).
     * </p>
     * 
	 * @param accountNumber
	 *     accountNumber of Account to use Add new account.
	 * @param balance
	 *     balance of Account to use Add new account.
	 * @param accounttype
	 *     accountTypre of Account to use Add new account.
	 * @param IFSCode
	 *     IFSCode of Branch to use Add new account.
	 * @param message
	 *     Display message using add attribute.
	 *     
	 * @return AddAccount
	 *     Return to the AddAccount JSP page with Branch address or status message(failure).
	 *     
	 * @throws DataBaseException
	 *     It handle all the custom exception in NetBanking application.
	 */
	@RequestMapping(value="/insertAccount", method = RequestMethod.POST)  
    public String addAccount(@RequestParam("accountNumber")String accountNumber, @RequestParam("balance")String balance, 
    		@RequestParam("accountType")String accountType, @RequestParam("ifscode")String IFSCode, ModelMap message) throws  DataBaseException {
		try { 
			message.addAttribute("message", branchManager.addAccount(new Account(accountNumber, Double.parseDouble(balance), accountType, branchManager.getBranchByIFSCode(IFSCode))));
		} catch(DataBaseException e) {
			message.addAttribute("message", e.getMessage());
		} finally {
			message.addAttribute("branches", branchManager.getBranches());
	     	return "addAccount";
		}
	}
	
	/**
     * It return to the ViewAccountByBranch JSP page and it form used for enter BranchId to view account detail.
     *  
     * @return ViewAccountByBranch
     *     Return to the ViewAccountByBranch JSP page.
     */
    @RequestMapping(value = "/viewAccountByBranch")
    public String viewAccountByBranch() {
        return "viewAccountByBranch";
    }
    
    
    /**
     * <p>
     *     This Method call to viewAccountByBranch method in branchManager.
     *     Return to the BranchIndex JSP page with list of accounts or status message(failure).
     * </p> 
     * 
     * @param IFSCode
     *     IFSCode of Branch. It use to retrieve all account detail in One Branch.
     * @param message
     *     Display message using add attribute.
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking application.
     *     
     * @return ViewAccountByBranch
     *     Return to the ViewAccountByBranch JSP page with list of accounts or status message(failure).
     */
    
    @RequestMapping(value="/getAccounts", method = RequestMethod.GET)  
    public String viewAccountByBranch (@RequestParam("IFSCode")String IFSCode, ModelMap message) {
        try { 
            List<Account> accounts = branchManager.viewAccountByBranch(IFSCode);
            if (Constants.SIZE != accounts.size()) {
                message.addAttribute("accounts", accounts);
            } else {
                message.addAttribute("message", "NO ACCOUNT STARTED IN THIS BRANCH ");
            }
        } catch (BranchDataException e) {
            message.addAttribute("message", e.getMessage()); 
        } catch(DataBaseException e) {
            message.addAttribute("message", e.getMessage());
        } finally {
             return "viewAccountByBranch";
        }
    }
    
}
