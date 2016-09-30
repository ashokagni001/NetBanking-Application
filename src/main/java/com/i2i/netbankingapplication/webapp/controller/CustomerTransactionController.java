package com.i2i.netbankingapplication.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.exception.TransactionCustomException;
import com.i2i.netbankingapplication.model.User;
import com.i2i.netbankingapplication.service.BeneficiaryManager;
import com.i2i.netbankingapplication.service.CustomerTransactionManager;
import com.i2i.netbankingapplication.service.UserManager;

/**
 * <p>
 *     When request comes from JSP page. 
 *     CustomerTransactionController performs add new transaction or view all notifications or view all transactions or transaction 
 *     permission or transaction ignore with model(transaction), Manager(transaction) and return the responses to JSP page.
 *     It handles the NumberFormatException, DataBaseException.
 *     If exception occurs it will write what type of exception occurred.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-27
 */
@Controller
public class CustomerTransactionController {
    
    @Autowired
    private BeneficiaryManager beneficiaryManager;
    
    @Autowired
    private CustomerTransactionManager customerTransactionManager;
    
    @Autowired
    private UserManager userManager;
    
    /**
     * <p>
     *     Get the transaction detail from JSP page and pass to getTransactionDetail method in customerTransactionManager.
     *     Return to JSP page transactionRegistration with status message(success or failure) Or Address object.
     * </p>
     * 
     * @param debitAccountNumber
     *     debitAccountNumber of transaction to use save transaction information.
     * @param creditAccountNumber
     *     creditAccountNumber of transaction to use save transaction information.
     * @param amount
     *     amount of transaction to use save transaction information.
     * @param message
     *     Display message using add attribute.
     *     
     * @return to the retrieveBeneficiaryByCustomerId jsp page with status message(success or failure).
     * 
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application.
     */
    
    @RequestMapping(value = "/insertTransaction", method = RequestMethod.GET)
    public String addTransaction(@RequestParam("creditAccountNumber") String creditAccountNumber,
            @RequestParam("amount") String amount, ModelMap message, final HttpServletRequest request) {
        try {
            User user = userManager.getUserByUsername(request.getRemoteUser());
            message.addAttribute("message", customerTransactionManager.addTransaction(user,
                    creditAccountNumber, Double.parseDouble(amount)));
            message.addAttribute("beneficiaries", beneficiaryManager.getBeneficiaryAccountByCustomerId(user));
        } catch (TransactionCustomException e) {
            message.addAttribute("message", e.getMessage());
        } catch (DataBaseException e) {
            message.addAttribute("message", e.getMessage());
        } finally {
            return "retrieveBeneficiaryByCustomerId";
        }
    }
    
    /**
     * <p>
     *     This Method call to getAllNotification method in customertransactionManager.
     *     Return to the viewTransactionNotifications JSP page with notification lists or status message(failure).
     * </p>
     * 
     * @return viewTransactionNotifications
     *     Return to the viewTransactionNotifications JSP page with notification lists or status message(failure).
     */    
    @RequestMapping(value = "/transactionNotifications")
    public ModelAndView getNotifications() {
        try {
             return new ModelAndView("viewTransactionNotifications", "notifications", customerTransactionManager.getAllNotifications());
        }catch(TransactionCustomException e) {
            return new ModelAndView ("viewTransactionNotifications", "message", e.getMessage());
        } catch (DataBaseException e) {
            return new ModelAndView ("viewTransactionNotifications", "message", e.getMessage());
        }
    }
    
    /**
     * <p>
     *     It method used for approver permission active.
     *     This Method call to transactionSuccess method in customerTransactionManager with transaction details.
     *     It return to the  viewAllTransaction JSP page with status message (Success or failure).
     * </p>
     *       
     * @param creditAccountNumber
     *     creditAccountNumber of Account.
     * @param amount
     *     amount of Transaction.
     * @param message
     *     Display message using add attribute.
     *     
     * @return viewAllTransaction
     *     Return to the viewAllTransaction JSP page with status message (Success or failure).
     */ 
    @RequestMapping(value = "/updateTransactionStatus", method = RequestMethod.GET)
    public String updateTransactionStatus(@RequestParam("id") int transactionId,
            @RequestParam("accountNumber") String accountNumber, @RequestParam("amount") Double amount,
            @RequestParam("action") String action, final HttpServletRequest request, ModelMap message) {
        try {
            customerTransactionManager.updateTransactionStatus(transactionId, accountNumber, amount, action, userManager.getUserByUsername(request.getRemoteUser()));
            message.addAttribute("message", "TRANSACTION ACTION SUCCESSFULLY");
            message.addAttribute("notifications", customerTransactionManager.getAllNotifications());
        } catch (DataBaseException e) {
            message.addAttribute("message", e.getMessage());
        } catch (TransactionCustomException e) {
            message.addAttribute("message", e.getMessage());
        } finally {
            return "viewTransactionNotifications";
        }
    }

    
    /**
     * <p>
     *     This Method call to getMiniStatementByCustomerId method in TransactionService.
     *     Return to the getMiniStatementByCustomerId JSP page with Customer MiniStatement or status message(failure).
     * </p>
     * 
     * @param customerId
     *     Id of Customer entered by user to view the corresponding record(MiniStatement).
     *     
     * @return RetrieveMiniStatementByCustomerId
     *     Return to the RetrieveMiniStatementByCustomerId JSP page with Customer MiniStatement or status message(failure).
     *
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application.
     */
    @RequestMapping(value="/viewMiniStatementByCustomerId", method = RequestMethod.GET)  
    public ModelAndView viewMiniStatementByCustomerId (final HttpServletRequest request, ModelMap message) {
        try {
            return new ModelAndView("viewCustomerMiniStatement", "miniStatement", customerTransactionManager.getCustomerMiniStatements(userManager.getUserByUsername(request.getRemoteUser())));
        } catch (DataBaseException e) {
            return new ModelAndView("viewCustomerMiniStatement", "message", e.getMessage());
        }
    }
    
    /**
     * <p>
     *     This Method call to getAllTransaction method in transactionService.
     *     Return to the RetrieveAllTransaction JSP page with transaction lists or status message(failure).
     * </p>
     *     
     * @return RetrieveAllTransaction
     *     Return to the RetrieveAllTransaction JSP page with list of transactions or status message(failure).
     */
    @RequestMapping(value = "/viewAllTransaction", method = RequestMethod.GET)
    public ModelAndView viewAllTransactions() {
        try {
            return new ModelAndView("retrieveAllTransaction", "transactions", customerTransactionManager.getAllTransactions());
        } catch (DataBaseException e) {
            return new ModelAndView("retrieveAllTransactions", "message", e.getMessage());
        }
    }
    
    /**
     * <p>
     *     This Method call to getCustomerAccount method in TransactionService with accountNumber of Account.
     *     Return to the AddBeneficiaryAccount JSP page with status message(success or failure).
     * </p>
     * 
     * @param accountNumber
     *     accountNumber of Account to use add new Beneficiary Account.
     * @param message
     *     Display message using add attribute.
     * @return AddBeneficiaryTransaction
     *     Return AddBeneficiaryTransaction JSP page with status(Success Or Failure).
     */
    @RequestMapping(value = "/addBeneficiaryTransaction", method = RequestMethod.GET)
    public String addBeneficiaryTransaction(@ModelAttribute("customerAccountNumber") String accountNumber, ModelMap message) {
        try {
            message.addAttribute("customerAccount", customerTransactionManager.getCustomerAccount(accountNumber));
        } catch (DataBaseException e) {
            message.addAttribute("message", e);
        } finally {
            return "addBeneficiaryTransaction";
        }
    }
}