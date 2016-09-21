package com.i2i.netbankingApplication.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.i2i.netbankingApplication.constant.Constant;
import com.i2i.netbankingApplication.exception.BranchDataException;
import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.model.Account;
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
     * If request come this method return the JSP page that contains options for Branch operation.
     * 
     * @return BranchIndex
     *     Return to the BranchIndex JSP page.
     */
    @RequestMapping(value = "/BranchIndex")
    public String branchIndex() {
        return "BranchIndex";
    }

    /**
     * If request come this method return to the AddBranch JSP page use to add the new branch.
     * 
     * @return AddBranch
     *     Return to the AddBranch JSP page.
     */
    @RequestMapping(value = "/addBranch")
    public String addBranch() {
        return "AddBranch";
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
    @RequestMapping(value = "/insertBranch", method = RequestMethod.POST)
    public String addBranch(@RequestParam("emailId") String emailId, ModelMap message) {
        String URL = "AddAddress";
        try {
            branchService.addBranch(emailId);
            message.addAttribute("BranchAddress", new Address());
        } catch (DataBaseException e) {
            message.addAttribute("message", e.getMessage());
            URL = "AddBranch";
        }
        return URL;
    }


    /**
     * <p>
     *     Get the address object from JSP page and pass to addAddress method in BranchService.
     *     Return to JSP page BranchIndex with Status message (Success with IFSC code or failure).
     * </p>
     * 
     * @param address
     *     Object of Address model class.
     * @param message
     *     Display message using add attribute.
     *     
     * @return BranchIndex
     *     Return to JSP page BranchIndex with status message(Success or Failure).
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking application.
     */
    @RequestMapping(value = "/address", method = RequestMethod.POST)
    public String addAddress(@ModelAttribute("BranchAddress") Address address, ModelMap message) {
        try {
            message.addAttribute("message", branchService.addAddress(address));
        } catch (BranchDataException e) {
            message.addAttribute("message", e.getMessage());
        } catch (DataBaseException e) {
            message.addAttribute("message", e.getMessage());
        } finally {
            return "AddBranch";
        }
    }

    /**
     * <p>
     *     This method delete the corresponding record(Branch IFSC).
     *     This Method call to deleteBranchById method in BranchService.
     *     Return to the RetrieveAllBranch JSP page with list of Branches or status message(failure).
     * </p>
     * 
     * @param BranchId
     *     Id of Branch entered by user to delete the corresponding record.
     *     
     * @return RetrieveAllBranch
     *     Return to the RetrieveAllBranch JSP page with list of Branches or status message(failure).
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking application.
     */
    @RequestMapping(value = "/deleteBranchById", method = RequestMethod.GET)
    public String deleteBranchById(@RequestParam("ifsc") String ifsc, ModelMap message) {
        try {
            message.addAttribute("message", branchService.deleteBranchById(ifsc));
            message.addAttribute("branches", branchService.getAllBranches());
        } catch (DataBaseException e) {
            message.addAttribute("message", e.getMessage());
            message.addAttribute("branches", branchService.getAllBranches());
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
     *     It handle all the custom exception in NetBanking application.
     */
    @RequestMapping(value = "/GetBranch")
    public String getAllBranches(ModelMap message) throws DataBaseException {
            message.addAttribute("branches", branchService.getAllBranches());
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
         * @return RetrieveAllBranch
         *     Return to the ReteriveAllBranch JSP page with list of Branches or status message(failure).
         *  
         * @throws DataBaseException
         *     It handle all the custom exception in NetBanking application.
         */
    @RequestMapping(value = "/getBranch", method = RequestMethod.GET)
    public ModelAndView viewBranchById(@RequestParam("ifsc") String ifsc) {
        try {
            if (ifsc.equals("all") || ifsc.equals("All") || ifsc.equals("ALL")) {
                return new ModelAndView("RetrieveAllBranch", "branches", branchService.getAllBranches());
            } else {
                Branch branch = branchService.getBranchById(ifsc);
                if (branch != null) {
                    return new ModelAndView("RetrieveAllBranch", "branch", branch);
                } else {
                    return new ModelAndView("RetrieveAllBranch", "message", "ENTER VALID IFSC ONLY");
                }
            }
        } catch (DataBaseException e) {
            return new ModelAndView("RetrieveAllBranch", "message", e.getMessage());
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
     *     It handle all the custom exception in NetBanking application.
     */
    @RequestMapping(value = "/viewBranchAddress", method = RequestMethod.GET)
    public ModelAndView viewAddressById(@RequestParam("addressId") int addressId, ModelMap message) {
        try {
            return new ModelAndView("RetrieveAddressById", "address", branchService.getAddressById(addressId));
        } catch (DataBaseException e) {
            return new ModelAndView("RetrieveAddressById", "message", e.getMessage());
        }
    }

    /**
     * Return to JSP page AddAccount.It used new add Customer account.
     * 
     * @return AddAccount
     *     Return to JSP page AddAccount.
     * @throws DataBaseException 
     *     It handle all the custom exception in NetBanking application.
     */
    @RequestMapping(value = "/AddAccount")
    public String addAccountForm(ModelMap message) throws DataBaseException {
        message.addAttribute("branches", branchService.getAllBranches());
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
     *     It handle all the custom exception in NetBanking application.
     */
    @RequestMapping(value = "/addAccount", method = RequestMethod.POST)
    public String addAccount(@RequestParam("accountNumber") String accountNumber, @RequestParam("balance") String balance,
        @RequestParam("accounttype") String accounttype, @RequestParam("ifscode") String ifsc, ModelMap message) throws DataBaseException {
        try {
            message.addAttribute("message", branchService.addAccount(accountNumber, Double.parseDouble(balance), accounttype, ifsc));
        } catch (DataBaseException e) {
            message.addAttribute("message", e.getMessage());
        } finally {
            message.addAttribute("branches", branchService.getAllBranches());
            return "AddAccount";
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
     * 
     * @param ifsc
     *     ifsc of Branch. It use to retrieve all account detail in One Branch.
     * @param message
     *     Display message using add attribute.
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking application.
     *     
     * @return ViewAccountByBranch
     *     Return to the ViewAccountByBranch JSP page with list of accounts or status message(failure).
     */

    @RequestMapping(value = "/getAccount", method = RequestMethod.GET)
    public String viewAccountByBranch(@RequestParam("ifsc") String ifsc, ModelMap message) {
        try {
            List < Account > accounts = branchService.viewAccountByBranch(ifsc);
            if (Constant.INITIALIZEVARAILABLEVALUE != accounts.size()) {
                message.addAttribute("accounts", accounts);
            } else {
                message.addAttribute("message", "NO ACCOUNT STARTED IN THIS BRANCH ");
            }
        } catch (BranchDataException e) {
            message.addAttribute("message", e.getMessage());
        } catch (DataBaseException e) {
            message.addAttribute("message", e.getMessage());
        } finally {
            return "ViewAccountByBranch";
        }
    }

}
