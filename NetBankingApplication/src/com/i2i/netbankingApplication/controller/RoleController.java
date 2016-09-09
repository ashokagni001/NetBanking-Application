package com.i2i.netbankingApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.model.Role;
import com.i2i.netbankingApplication.service.RoleService;

@Controller
public class RoleController {
	RoleService roleService = new RoleService();

	@RequestMapping(value = "/RoleIndex")
	public String login() {
		return "RoleIndex";
	}

	@RequestMapping(value = "/addRole")
	public String addForm(ModelMap model) throws DataBaseException {
		if (roleService.isRoleAvailable()) {
			model.addAttribute("roles", roleService.getAllRole());
		} else {
			model.addAttribute("message", "Sorry role is not present");
		}
		return "AddRole";
	}

	@RequestMapping(value = "/insertRole", method = RequestMethod.GET)
	public String addRole(@RequestParam("role") String roleId, @RequestParam("customerId") String customerId,
			ModelMap message) throws DataBaseException {
		try {
			roleService.insertRole(roleId, customerId);
			message.addAttribute("message", "INFORMATION SAVED SUCESSFULLY");
		} catch (DataBaseException  exp) {
			message.addAttribute("message", "SORRY INFORMATION CAN NOT SAVED PLEASE TRY AGAIN" + exp);
			message.addAttribute("roles", roleService.getAllRole());
		}
		return "AddRole";
	}

	@RequestMapping(value = "/deleteRole")
	public String deleteForm() {
		return "DeleteRole";
	}

	@RequestMapping(value = "/deleteRoleById", method = RequestMethod.GET)
	public String deleteRoleById(@RequestParam("Id") String Id, ModelMap message) throws DataBaseException {
		try {
			roleService.deleteRoleBYId(Id);
			message.addAttribute("message", "ID DELETED SUCESSFULLY");
		} catch (DataBaseException e) {
			message.addAttribute("message", "ENTER VALID ID ONLY");
		}
		return "DeleteRole";
	}

	@RequestMapping(value = "/getRoleById")
	public String viewForm() {
		return "GetRoleById";
	}

	@RequestMapping(value = "/getRoleById", method = RequestMethod.GET)
	public ModelAndView viewRoleById(@RequestParam("Id") String Id, ModelMap message) throws DataBaseException {
		try {
			return new ModelAndView("RetrieveRoleById", "role", roleService.getRoleById(Id));
		} catch (DataBaseException e) {
			return new ModelAndView("RetrieveRoleById", "message", "ENTER VALID IFSC ONLY");
		}
	}

}
