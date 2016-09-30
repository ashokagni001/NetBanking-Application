package com.i2i.netbankingapplication.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.i2i.netbankingapplication.Constants;
import com.i2i.netbankingapplication.exception.BranchDataException;
import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.model.Account;
import com.i2i.netbankingapplication.model.Branch;
import com.i2i.netbankingapplication.service.BranchManager;
import com.i2i.netbankingapplication.service.UserManager;

/**
 * <p>
 *     When request comes from jsp page. 
 *     Branchcontroller performs add or fetch or fetchAll with model(Branch),
 *     BranchManager and return the responses to jsp page.
 *     It handles the DataBaseException.
 *     If exception occurs it will write what type of exception occurred.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-27.
 */
@Controller
public class BranchController {
    
    @Autowired
    private BranchManager branchManager;
    
    @Autowired
    private UserManager userManager;
    
    /**
     * If request come this method return to the AddBranch jsp page used to add the new branch.
     * 
     * @return AddBranch
     *     Return to the AddBranch jsp page.
     */
    @RequestMapping(value = "/addBranch")
    public String addBranch(ModelMap message) {
        message.addAttribute("Branch", new Branch());
        return "addBranch";
    }
    
    /**
     * <p>
     *     Get the Branch emailId from jsp page and pass to addBranch method in BranchService.
     *     Return to jsp page BranchIndex with status message(success or failure) or Address object.
     * </p>
     * 
     * @param emailId
     *     emailId of entered by user to add new the branch.
     * @param message
     *     Display message using add attribute.
     *     
     * @return contains url of addBranch page with Status message(success or failure).
     * 
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking application.
     */
    @SuppressWarnings("finally")
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
     *     This Method call to getAllBranches method in branchManager.
     *     Return to the retrieveBranches jsp page with list of Branches or status message.
     * </p>
     * 
     * @return contains url of retrieveBranches page with list of Branches or Status message(failure).
     *     
     * @throws DataBaseException 
     *     It handle all the custom exception in NetBanking application.
     */ 
    @RequestMapping(value = "/viewBranches")
    public String getAllBranches(ModelMap message) {
        try {
            message.addAttribute("branches", branchManager.getBranches());
        } catch (DataBaseException e) {
            message.addAttribute("message", e);
        }
        return "retrieveBranches";
    }
    
    /**
     * <p>
     *     This Method call to getAllBranch method in branchManager.
     *     Return to the RetrieveAllBranch jsp page with list of Branches or status message(failure).
     * </p>
     * 
     * @param ifscode
     *     ifscode of Branch entered by user to view the corresponding record.
     *     
     * @return retrieveBranches jsp page with list of Branches or status message(failure).
     *  
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking application.
     */
    @SuppressWarnings("finally")
    @RequestMapping(value="/getBranchByIFSCode", method = RequestMethod.GET)  
    public String getBranchByIfscode(@RequestParam("IFSCode")String ifscode, ModelMap message) {
        try {
            if (ifscode.equals("all") || ifscode.equals("All") || ifscode.equals("ALL")) {
                message.addAttribute("branches", branchManager.getBranches());
            } else  {
                Branch branch = branchManager.getBranchByIfscode(ifscode);
                if (branch != null) {
                    message.addAttribute("branch", branch);
                } else {
                    message.addAttribute("message", "Enter valid ifscode only.");
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
     * <p>
     *     Return to contains url of addAccount page.
     *     This page used for add new customer account.
     * </p>
     * 
     * @param message 
     *     Display message using add attribute.
     *     
     * @return contains url of addAccount add page.
     */
    @RequestMapping(value = "/addAccount")
    public String addAccountForm(ModelMap message) {
        try {
            message.addAttribute("branches", branchManager.getBranches());
        } catch (DataBaseException e) {
            message.addAttribute("message", e);
        } 
        return "addAccount";
    }
    
    /**
     * <p>
     *     This Method call to getAccount method in BranchService.
     *     Return to the BranchIndex jsp page with success or status message(failure).
     * </p>
     * 
     * @param accountNumber
     *     accountNumber of Account to used Add new account.
     * @param balance
     *     balance of Account to used Add new account.
     * @param accounttype
     *     accountTypre of Account to used Add new account.
     * @param ifscode
     *     ifscode of Branch to use add new account.
     * @param message
     *     Display message using add attribute.
     *     
     * @return contains url of addAccount page with list of branches or status message(failure).
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking application.
     */
    @SuppressWarnings("finally")
    @RequestMapping(value="/insertAccount", method = RequestMethod.POST)  
    public String addAccount(@RequestParam("accountNumber")String accountNumber, @RequestParam("balance")String balance, 
            @RequestParam("accountType")String accountType, @RequestParam("ifscode")String ifscode, ModelMap message) throws DataBaseException {
        try { 
            message.addAttribute("message", branchManager.addAccount(new Account(accountNumber, Double.parseDouble(balance), accountType, branchManager.getBranchByIfscode(ifscode))));
        } catch(DataBaseException e) {
            message.addAttribute("message", e.getMessage());
        } finally {
            message.addAttribute("branches", branchManager.getBranches());
            return "addAccount";
        }
    }
    
    /**
     * <p>
     *     It return to the ViewAccountByBranch jsp page and
     *     it form used for enter BranchId to view account detail.
     * </p>
     *  
     * @return contains url of viewAccountByBranch page.
     */
    @RequestMapping(value = "/viewAccountByBranch")
    public String viewAccountByBranch() {
        return "viewAccountByBranch";
    }
    
    
    /**
     * <p>
     *     This Method call to viewAccountByBranch method in branchManager.
     *     Return to the BranchIndex jsp page with list of accounts or status message(failure).
     * </p> 
     * 
     * @param ifscode
     *     ifscode of Branch. It used to retrieve all account detail in a branch.
     * @param message
     *     Display message using add attribute.
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking application.
     *     
     * @return contains url of viewAccountByUser page with list of accounts based on 
     *     ifscode or status message(failure).
     */
    @SuppressWarnings("finally")
    @RequestMapping(value="/getAccounts", method = RequestMethod.GET)  
    public String viewAccountByBranch (@RequestParam("IFSCode")String ifscode, ModelMap message) {
        try { 
            List<Account> accounts = branchManager.viewAccountByBranch(ifscode);
            if (Constants.SIZE != accounts.size()) {
                message.addAttribute("accounts", accounts);
            } else {
                message.addAttribute("message", "NO ACCOUNT STARTED IN THIS BRANCH");
            }
        } catch (BranchDataException e) {
            message.addAttribute("message", e.getMessage()); 
        } catch(DataBaseException e) {
            message.addAttribute("message", e.getMessage());
        } finally {
             return "viewAccountByBranch";
        }
    }
    
    /**
     * <p>
     *     This method passes the user object into it Service class.
     *     Return the viewAccountByUser jsp page with user account detail
     *     or status message(failure).
     * </p>
     * 
     * @param request
     *     It is used for get the user.
     * @return contains url of viewAccountByUser page with user account detail
     *     or status message(failure).
     */
    @RequestMapping(value="/viewUserAccount")
    public ModelAndView viewAccountByUser(final HttpServletRequest request) {
        try {
            return new ModelAndView("viewAccountByUser", "account", branchManager.getAccountByAccountNumber(userManager.getUserByUsername(request.getRemoteUser()).getAccountNumber()));
       } catch (DataBaseException e) {
           return new ModelAndView ("viewAccountByUser", "message", e.getMessage());
       }
    }
    
    /**
     * <p>
     *     This method passes the accountNumber into it Service class.
     *     Return the viewAccountByUser jsp page with user account detail
     *     or status message(failure).
     * </p>
     * 
     * @param accountNumber
     *     accountNumber of Account. It is used view the customer account detail.
     *     
     * @return contains url of viewAccountByUser page with user account detail
     *     or status message(failure).
     */
    @RequestMapping(value="/viewAccountByAccountNumber", method = RequestMethod.GET)
    public ModelAndView viewAccountByAccountNumber(@RequestParam("accountNumber")String accountNumber) {
        try {
            return new ModelAndView("viewAccountByUser", "account", branchManager.getAccountByAccountNumber(accountNumber));
        } catch (DataBaseException e) {
            return new ModelAndView ("viewAccountByUser", "message", e.getMessage());
        }
    }
}
