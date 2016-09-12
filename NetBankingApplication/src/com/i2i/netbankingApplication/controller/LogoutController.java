package com.i2i.netbankingApplication.controller;

import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * When request comes from JSP page. Logout controller performs session invalid.
 * 
 *  @author ASHOK
 * 
 * @created 2016-09-10
 */
@Controller
public class LogoutController extends HttpServlet {
	
	/**
	 * Performs session invalidate.
     * 
	 * @param message
	 *        Display message using add attribute.
	 * 
	 * @param req
	 *        Request of HttpServletRequest.
	 * 
	 * @param res
	 *        Response of HttpServletResponse.
	 * 
	 * @param session
	 *        Session of httpSeesion.
	 * 
	 * @return login
	 *         Return to the login jsp page.
	 */
	@RequestMapping(value = "/logoutController")
    public String logout(ModelMap message, HttpSession session) {
        session.invalidate();
        message.addAttribute("message", "LOGOUT SUCCESSFULLY");
        return "login";
    }
}
