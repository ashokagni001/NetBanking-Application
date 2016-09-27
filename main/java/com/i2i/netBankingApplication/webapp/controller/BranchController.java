package com.i2i.netBankingApplication.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.i2i.netBankingApplication.exception.DataBaseException;
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
    @RequestMapping(value = "/addbranch")
    public String addBranch(ModelMap message) {
        message.addAttribute("Branch", new Branch());
        return "addbranch";
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
            branchManager.addBranch(branch);
        } catch (DataBaseException e) {
            message.addAttribute("message", e.getMessage()); 
        } finally {
            return ("redirect:/home");
        }
    }
    
}
