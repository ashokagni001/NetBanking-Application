package com.i2i.netbankingapplication.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.exception.TransactionCustomException;
import com.i2i.netbankingapplication.service.BeneficiaryManager;
import com.i2i.netbankingapplication.service.UserManager;

/**
 * <p>
 *     When request comes from jsp page. 
 *     BeneficiaryController performs add or update or fetch or fetchAll with model(Branch),
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
public class BeneficiaryController {
    
    @Autowired
    private BeneficiaryManager beneficiaryManager;
    
    @Autowired
    private UserManager userManager;
    
    /**
     * <p> 
     *     This form used to add new beneficiary account. 
     *     Return contains url of addBeneficiaryAccount page to use add new beneficiary account.
     * </p>
     * 
     * @return contains url of addBeneficiaryAccount page.
     */
    @RequestMapping(value = "/addBeneficiaryAccount")
    public String addBeneficiaryAccountForm() {
        return "addBeneficiaryAccount";
    }
    
    /**
     * <p>
     *     This Method call to addBeneficiaryAccount method in TransactionService.
     *     return contains url of addBeneficiaryAccount add page with status message(success or failure).
     * </p>
     * 
     * @param accountNumber
     *    accounNumber of account entered by user add new BeneficiaryAccount.
     * @param ifscode
     *    ifscode of Branch entered by user add new BeneficiaryAccount.
     * @param request
     *    It is used for get the user.
     *    
     * @return contains url of addBeneficiaryAccount page 
     *    with status message(success or failure).
     */
    @RequestMapping(value = "/addBeneficiary")
    public ModelAndView addBeneficiaryAccount(@RequestParam("accountNumber")String accountNumber, 
            @RequestParam("IFSCode")String ifscode, final HttpServletRequest request) {
        try { 
            return new ModelAndView ("addBeneficiaryAccount", "message", beneficiaryManager.addBeneficiaryAccount(userManager.getUserByUsername(request.getRemoteUser()), accountNumber, ifscode)); 
        } catch(TransactionCustomException e) {
            return new ModelAndView ("addBeneficiaryAccount", "message", e.getMessage());
        } catch (DataBaseException e) {
            return new ModelAndView ("addBeneficiaryAccount", "message", e.getMessage());
        }
    }
    
    /**
     * <p>
     *     This Method call to getAllBeneficiaries method in TransactionService.
     *     Return to the getAllBeneficiaries JSP page with status(Success Or Failure).
     *     This method handle the TransactionCustomException and DataBaseException.
     *     TransactionCustomException is our business logic exception. 
     * </p>
     * 
     * @return to the RetrieveAllBeneficiaryNotification JSP page with status(success or failure).
     */
    @RequestMapping(value = "/beneficiaryNotifications")
    public ModelAndView fetchBeneficiaryNotifications() {
        try {
            return new ModelAndView("retrieveAllBeneficiaryNotification", "beneficiaryNotifications", beneficiaryManager.getAllBeneficiaries());
        } catch(TransactionCustomException e) {
            return new ModelAndView ("retrieveAllBeneficiaryNotification", "message", e.getMessage());
        } catch (DataBaseException e) {
            return new ModelAndView("retrieveAllBeneficiaryNotification", "message", e.getMessage());
        }
    }
    
    /**
     * <p>
     *     Update the beneficiary account status based on beneficiary id.
     *     Return to the retrieveAllBeneficiaryNotification JSP page with list of beneficiary notifications and
     *     status message(success or failure).
     * </p>
     * 
     * @param beneficiaryId
     *     beneficiaryId of Beneficiary to update beneficiary account information.
     * @param action
     *     It is beneficiary status to update beneficiary account information.
     * @param message
     *     Display message using add attribute.
     *     
     * @return to the retrieveAllBeneficiaryNotification JSP page with list of beneficiary notifications and
     *     status message(success or failure).
     */
    @SuppressWarnings("finally")
    @RequestMapping(value = "/updateBeneficiaryAccount", method = RequestMethod.GET)
    public String updateBeneficiaryAccount(@RequestParam("id") int beneficiaryId,@RequestParam("action") String action, ModelMap message) {
        try {
            beneficiaryManager.updateBeneficiaryAccount(beneficiaryId, action);
            message.addAttribute("message", "YOUR ACTION SUCCESSFULLY");
            message.addAttribute("beneficiaryNotifications", beneficiaryManager.getAllBeneficiaries());
        } catch (DataBaseException e) {
            message.addAttribute("message", e.getMessage());
        } catch (TransactionCustomException e) {
            message.addAttribute("message", e.getMessage());
        } finally {
            return "retrieveAllBeneficiaryNotification";
        }
    }
    
    /**
     * <p> 
     *     Return the beneficiary account detail based on userId.
     *     This method call to getBeneficiaryAccountByCustomerId method in BeneficiaryManager with user.
     *     Return to the reterieveBeneficiaryByCustomerId JSP page with list of beneficiary account detail or
     *     status message(failure).
     * </p>
     * 
     * @param request
     *     It is used for get the user object.
     *     
     * @return contains url of reterieveBeneficiaryByCustomerId page with list of beneficiary account detail or
     *     status message(failure).
     */
    @RequestMapping(value = "/viewAllBeneficiaryAccountDetail", method = RequestMethod.GET)
    public ModelAndView viewAllBeneficiaryAccountDetail(final HttpServletRequest request) {
        try {
            return new ModelAndView ("retrieveBeneficiaryByCustomerId", "beneficiaries", 
                    beneficiaryManager.getBeneficiaryAccountByCustomerId(userManager.getUserByUsername(request.getRemoteUser())));
        } catch(TransactionCustomException e) {
            return new ModelAndView ("retrieveBeneficiaryByCustomerId", "message", e.getMessage());
        } catch (DataBaseException e) {
            return new ModelAndView ("retrieveBeneficiaryByCustomerId", "message", e.getMessage());
        }
    }
}
