package com.i2i.netbankingApplication.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.service.CustomerService;



/**
 * <p>
 *     When request comes from JSP page. Login controller performs login verification using
 *     service(Customer) and return the response to JSP page.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-10
 *
 */
@Controller
public class LoginController {
	private CustomerService customerService = new CustomerService();
	
   /**
	* @return login
	*     Return to the login JSP page.
	*/
	@RequestMapping(value = "/login")
    public String login(){
		return "redirect:login.jsp";
	}
	
	/**
	 * <p> 
	 *     Get the Customer Id and passWord from JSP page and pass 
     *     to ifValidateUser and checkIfRole methods in CustomerService.
     *     If Approver login goes to approver home page.
     *     If user login goes view only his operation only.
     *     If login fail goes to login page with error message.
     * </p> 
     * 
	 * @param customerId
	 *     Id of Customer entered by user to login.
	 * 
	 * @param password
	 *     password of Customer entered by user to login.
	 * 
	 * @param message
	 *     Display message using add attribute.
	 * 
	 * @param session
	 *     Object of httpSeesion. Adds the id and role of user by using setAttribute method.
	 * 
	 * @return ApproverHomePage
	 *     Return to the menu JSP page to view the NET BANKING operations.UserHomePage
	 *     
	 * @return UserHomePage
	 *     Return to the UserHomePage JSP page to view user operations.
	 * 
	 * @return login
	 *     If login fails return to the login JSP page with error message.
	 * 
     * @throws DataBaseException
     *     If there is an error in the given data like BadElementException.
     */
	@RequestMapping(value = "/loginController", method = RequestMethod.POST)
    public String loginVerification(@RequestParam("customerId")String customerId, @RequestParam("password")String password, ModelMap message,HttpSession session) {
		try {
		    if (customerService.ifValidateUser(customerId, password)) {
				if (customerService.checkIfRole(customerId)){
					session.setAttribute("id", customerId);
					session.setAttribute("role", "approver");
					return "ApproverHomePage";
				} else {
					session.setAttribute("id", customerId);
					session.setAttribute("role", "user");
					return "UserHomePage";
				}
			} else {
				message.addAttribute("message", "CHECK YOUR USERNAME and PASSWORD..");
				return "redirect:login.jsp";
			} 
        } catch (DataBaseException e) {
        	message.addAttribute("message", "SOME PROBLEM OCCUR PLEASE TRY AGAIN LATER");
            return "redirect:login.jsp";
        }
	}
	
	/**
	 * <p>
	 *     Adds the id and role of user by using setAttribute method.
	 *     Return to the UserHomePage JSP page to view user operations.
	 * </p>
	 * 
	 * @param customerId
	 *     Id of Customer.
	 *    
	 * @param session
	 *     Object of httpSeesion. Adds the id and role of user by using setAttribute method.
	 *    
	 * @return UserHomePage 
	 *     Return to the UserHomePage JSP page to view user operations.
	 */
	@RequestMapping(value = "/userHomePage")
    public String userHomePage (HttpSession session) {
		session.removeAttribute("role");
		session.setAttribute("role", "user");
		return "UserHomePage";
	}
	
	/**
	 * This method return to the approverIndexPage JSP page to view approver operations.
	 * 
	 * @return approverIndexPage
	 *     Return to the approverIndexPage JSP page to view approver operations.
	 */
	@RequestMapping(value = "/approverIndexPage")
    public String approverIndexPage(){ 
		return "ApproverIndexPage";
	}
}


 
