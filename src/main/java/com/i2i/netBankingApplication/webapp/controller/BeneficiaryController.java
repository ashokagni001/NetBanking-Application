package com.i2i.netBankingApplication.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
            @RequestParam("IFSCode")String IFSCode, final HttpServletRequest request) {
        try { 
            return new ModelAndView ("addBeneficiaryAccount", "message", beneficiaryManager.addBeneficiaryAccount(userManager.getUserByUsername(request.getRemoteUser()), accountNumber, IFSCode)); 
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
	 * @return statusMessage
	 *     Return to the RetrieveAllBeneficiaryNotification with status(Success Or Failure).
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
	 * 
	 * @param beneficiaryId
	 * @param message
	 *     Display message using add attribute.
	 * @return
	 */
	@RequestMapping(value = "/beneficiaryRequestSuccess", method = RequestMethod.GET)
	public String beneficiaryRequestActive(@RequestParam("id") int beneficiaryId, ModelMap message) {
		try {
			beneficiaryManager.beneficiaryAccountActive(beneficiaryId);
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
	
	@RequestMapping(value = "/beneficiaryRequestCancel", method = RequestMethod.GET)
	public String beneficiaryRequestDeactive(@RequestParam("id") int beneficiaryId, ModelMap message) {
		try {
			beneficiaryManager.beneficiaryAccountDeactive(beneficiaryId);
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
     *     This Method call to getBeneficiaryAccountByCustomerId method in TransactionService with customerId.
     *     Return JSP page (ReterieveBeneficiaryByCustomerId Or UserHomePage) with status(Success Or Failure).
     * </p>
     * 
	 * @param request
	 *     It is used for get the customer Id.
	 * @return statusMessage
	 *     Return JSP page (ReterieveBeneficiaryByCustomerId Or UserHomePage) with status(Success Or Failure).
	 */
	@RequestMapping(value = "/viewAllBeneficiaryAccountDetail", method = RequestMethod.GET)
	public ModelAndView viewAllBeneficiaryAccountDetail(final HttpServletRequest request) {
		try {
            return new ModelAndView ("retrieveBeneficiaryByCustomerId", "beneficiaries", beneficiaryManager.getBeneficiaryAccountByCustomerId(userManager.getUserByUsername(request.getRemoteUser())));
    	} catch(TransactionCustomException e) {
    		return new ModelAndView ("retrieveBeneficiaryByCustomerId", "message", e.getMessage());
    	} catch (DataBaseException e) {
            return new ModelAndView ("retrieveBeneficiaryByCustomerId", "message", e.getMessage());
        }
	}
    
}