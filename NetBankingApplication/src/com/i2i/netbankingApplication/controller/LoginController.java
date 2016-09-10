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
 * @author ASHOK
 * 
 * @created 2016-09-10
 *
 */
@Controller
public class LoginController {
	HttpSession httpSession;
	
   /**
	* @return login
	*     Return to the login JSP page.
	*/
	@RequestMapping(value = "/login")
    public String login(){
		return "login";
	}
	
	/**
	 * <p> 
	 *     Get the Customer Id and passWord from JSP page and pass 
     *     to ifValidateUser and checkIfRole methods in CustomerService.
     *     If Admin login goes to menu page and permits all operations.
     *     If Customer login goes view only his data only.
     *     If login fail goes to login page with error message.
     * </p> 
     * 
	 * @param customerId
	 *        Id of Customer entered by user to login.
	 * 
	 * @param password
	 *        Password of Customer entered by user to login.
	 * 
	 * @param message
	 *        Display message using add attribute.
	 * 
	 * @param session
	 *        Object of httpSeesion. Adds the id and role of employee by using setAttribute method.
	 * 
	 * @return menu
	 *         Return to the menu JSP page to view the NET BANKING operations.
	 * 
	 * @return login
	 *         If login fails return to the login JSP page with error message.
	 * 
	 * @throws NumberFormatException
     *         If the data is very large.
     * 
     * @throws DataBaseException
     *         If there is an error in the given data like BadElementException.
     */
	@RequestMapping(value = "/loginController", method = RequestMethod.POST)
    public String loginVerification(@RequestParam("customerId")String customerId, @RequestParam("password")String password, ModelMap message,HttpSession session) {
		try {
			CustomerService customerService = new CustomerService();
		    if (customerService.ifValidateUser(customerId, password)) {
				if (customerService.checkIfRole(customerId)){
					session.setAttribute("id", customerId);
					session.setAttribute("role", "approver");
					return "ApproverHomePage";
				} else {
					session.setAttribute("id",customerId);
					session.setAttribute("role", "user");
					return "UserHomePage";
				}
			} else {
				message.addAttribute("message", "CHECK YOUR USERNAME and PASSWORD..");
				return "login";
			} 
		} catch (NumberFormatException e) {
			message.addAttribute("message", "PLEASE ENTER VALID NUMBER");
		    return "login";
        } catch (DataBaseException e) {
        	message.addAttribute("message", "SOME PROBLEM OCCUR PLEASE TRY AGAIN LATER");
            return "login";
        }
	}
	
	@RequestMapping(value = "/userHomePage")
    public String userHomePage(@RequestParam("customerId")String customerId, HttpSession session) {
		session.invalidate();
		httpSession.setAttribute("id", customerId);
		httpSession.setAttribute("role", "user");
		return "UserHomePage";
	}
	
	@RequestMapping(value = "/approverHomePage")
    public String approverHomePage(){ 
		return "approverIndexPage";
	}
}


 
