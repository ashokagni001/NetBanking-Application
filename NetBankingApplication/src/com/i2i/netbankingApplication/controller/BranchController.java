package com.i2i.netbankingApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.model.Address;
import com.i2i.netbankingApplication.model.Branch;
import com.i2i.netbankingApplication.service.BranchService;

@Controller
public class BranchController {
	BranchService branchService = new BranchService();
	
	@RequestMapping(value = "/BranchIndex")
	public String login() {
		return "BranchIndex";
	}
	@RequestMapping(value = "/addBranch")
	public String getBranch(ModelMap model) {
		model.addAttribute("Branch", new Branch());
		return "AddBranch";
	}
	
		@RequestMapping(value="/insertBranch", method = RequestMethod.POST)
    public String addBranch(@RequestParam("emailId") String emailId, ModelMap message) {  
		try {
		    branchService.getBranch(emailId);
            message.addAttribute("Address", new Address());
            return "AddAddress";
		} catch (DataBaseException e) {
    		message.addAttribute("message", "ENTER VALID DATA ONLY"); 
        }
		return "BranchIndex";
    }
	
	@RequestMapping(value="/address", method = RequestMethod.POST)
    public String addAddress(@ModelAttribute("address") Address address, ModelMap message) {  
		try {
            branchService.getAddress(address);
            System.out.println(address);
            return "BranchIndex";
		}  catch (DataBaseException e) {
    		message.addAttribute("message", "ENTER VALID DATA ONLY"); 
        }
		return "BranchIndex";
    }
	
	@RequestMapping(value = "/deleteBranch")
	public String deleteBranch() {
		return "DeleteBranch";
	}
	
	@RequestMapping(value="/deleteBranchById", method = RequestMethod.GET)
    public String deleteBranch(@RequestParam("ifsc")String ifsc, ModelMap message) {
    	try {       
            branchService.deleteBranchById(ifsc);
            message.addAttribute("message", "BRANCH DELETED SUCESSFULLY");
    	} catch (DataBaseException e) {
    		message.addAttribute("message", "ENTER VALID ID ONLY"); 
        }
        return "DeleteBranch";
    }
	
	@RequestMapping(value = "/getBranch")
	public String getBankById() {
		return "GetBranchById";
	}
	
	@RequestMapping(value="/getBranchById", method = RequestMethod.GET)  
    public ModelAndView viewEmployeeById (@RequestParam("ifsc")String ifsc, ModelMap message) {
        try {
            return new ModelAndView("RetrieveBranchById","branch", branchService.getBranchById(ifsc));
        } catch (DataBaseException e) {
        	return new ModelAndView("RetrieveBranchById","message", "ENTER VALID IFSC ONLY");
        }
    }
	
	@RequestMapping(value="/getAllBranches")  
    public ModelAndView getAllEmployee() {
        try {
        	return new ModelAndView ("RetrieveAllBranch", "branches", branchService.getAllBranch()); 
        } catch (DataBaseException e) {
        	return new ModelAndView ("RetrieveAllBranch", "message", e.getMessage().toString());
        } 
    } 
}
