package com.i2i.netBankingApplication.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.exception.TransactionCustomException;
import com.i2i.netBankingApplication.model.User;
import com.i2i.netBankingApplication.service.BeneficiaryManager;
import com.i2i.netBankingApplication.service.UserManager;

@Controller
public class BeneficiaryController {
    
    @Autowired
    private BeneficiaryManager beneficiaryManager;
    
    @Autowired
    private UserManager userManager;
    
    /**
     * <p> 
     *     This form used to view transaction by date.
     *     It is return to the viewTransactionBYDate JSP page.
     * </p>
     * 
     * @return AddTransaction
     *     Return to the AddTransaction JSP page.
     */
    @RequestMapping(value = "/addBeneficiaryAccount")
    public String addBeneficiaryAccountForm() {
        return "addBeneficiaryAccount";
    }
    
    /**
     * <p>
     *     This Method call to addBeneficiaryAccount method in TransactionService.
     *     Return to the AddBeneficiaryAccount JSP page with status message(success or failure).
     * </p>
     * 
     * @param accountNumber
     *    accounNumber of account entered by user add new BeneficiaryAccount.
     * @param IFSCode
     *    IFSCode of Branch entered by user add new BeneficiaryAccount.
     * @param session
     *    It is used for get the customer Id.
     *    
     * @return statusMessage
     *    Return to the JSP page(AddBeneficiaryAccount Or UserHomePage) 
     *    with status message(success or failure).
     */
    @RequestMapping(value = "/addBeneficiary")
    public ModelAndView addBeneficiaryAccount(@RequestParam("accountNumber")String accountNumber, 
            @RequestParam("IFSCode")String IFSCode, final HttpServletRequest requset, User user) {
        try { 
            user = userManager.getUserByUsername(requset.getRemoteUser());
            return new ModelAndView ("addBeneficiaryAccount", "message", beneficiaryManager.addBeneficiaryAccount(user, accountNumber, IFSCode)); 
        } catch(TransactionCustomException e) {
            return new ModelAndView ("addBeneficiaryAccount", "message", e.getMessage());
        } catch (DataBaseException e) {
            return new ModelAndView ("addBeneficiaryAccount", "message", e.getMessage());
        }
    }
    
    
}
