package com.i2i.netbankingApplication.controller;

import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * When request comes from JSP page. Logout controller performs session invalid.
 * 
 *  @author TEAM-2
 * 
 * @created 2016-09-10
 */
@Controller
public class LogoutController extends HttpServlet {
	
	/**
	 * Performs session invalidate.
     * 
	 * @param message
	 *     Display message using add attribute.
	 * @param session
	 *     Session of httpSeesion.
	 * 
	 * @return Information
	 *     Return to the Information JSP page with message.
	 */
	@RequestMapping(value = "/logoutController")
    public String logout(ModelMap message, HttpSession session) {
        session.invalidate();
        message.addAttribute("message", "LOGOUT SUCCESSFULLY");
        return "Information";
    }
}
