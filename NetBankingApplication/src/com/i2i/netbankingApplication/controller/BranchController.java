package com.i2i.netbankingApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.i2i.netbankingApplication.model.Branch;
import com.i2i.netbankingApplication.service.BranchService;

@Controller
public class BranchController {
	BranchService branchService = new BranchService();
	@RequestMapping(value = "/open")
	public String login() {
		return "BranchIndex";
	}
	
	@RequestMapping(value = "/addBranch")
	public String addBranche(ModelMap model) {
		model.addAttribute("Branch", new Branch());
		return "AddBranch";
	}
	
	@RequestMapping(value="/insertBranch", method = RequestMethod.POST)
    public String addBranch(@ModelAttribute("employee") Branch branch, ModelMap message) {
    	try {       
            branchService.getBranch(branch);
            message.addAttribute("message", "BRANCH ADDED SUCESSFULLY");
    	} catch (NumberFormatException e) {
    		message.addAttribute("message", "ENTER VALID EMAIL ONLY"); 
        }
        return "Addbranch";
    }
}
