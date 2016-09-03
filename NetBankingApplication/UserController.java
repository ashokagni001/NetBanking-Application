package com.netbanking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.netbanking.exception.UserException;
import com.netbanking.model.User;
import com.netbanking.service.UserService;

@Controller
public class UserController {
    UserService userService = new UserService();
	
	@RequestMapping("/CustomerRegistration") 
	 public String addForm(ModelMap model) {
		 model.addAttribute("User", new User());
		 return "CustomerRegistration";
	 }
	
	@RequestMapping(value="/register", method = RequestMethod.POST)  
	 public ModelAndView save(@ModelAttribute("User") User user) throws IOException, ServletException {  
        try {
        	userService.insertUser(user);
       	 return new ModelAndView("acknowledgement","insertSuccess","User added successfully");
        } catch(Exception exc) {
       	 return new ModelAndView("acknowledgement", "insertFailure", "Sorry User can't added please try again");
        }
	 }
}
