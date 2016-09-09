package com.i2i.netbankingApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.service.RoleService;

@Controller
public class RoleController {
	RoleService roleService = new RoleService();

	@RequestMapping(value = "/addUserRole")
	public String addForm(ModelMap model) throws DataBaseException {
		if (roleService.isRoleAvailable()) {
			model.addAttribute("roles", roleService.getAllRole());
		} else {
			model.addAttribute("message", "Sorry role is not present");
		}
		return "AddUserRole";
	}

	@RequestMapping(value = "/insertRole", method = RequestMethod.GET)
	public String addRole(@RequestParam("customerId") String customerId, @RequestParam("role") String roleId,
			ModelMap message) throws DataBaseException {
		try {
			roleService.insertRole(customerId, roleId);
			message.addAttribute("message", "INFORMATION SAVED SUCESSFULLY");
		} catch (DataBaseException  exp) {
			message.addAttribute("message", "SORRY INFORMATION CAN NOT SAVED PLEASE TRY AGAIN" + exp);
			message.addAttribute("roles", roleService.getAllRole());
		}
		return "AddUserRole";
	}
}
