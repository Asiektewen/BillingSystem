package com.telecom.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import com.telecom.billing.services.UserService;

/**
 * @author zhangle
 *
 */
@Controller
@SessionAttributes({ "user" })
@RequestMapping("/admin/customer")
public class CustomerController {
	@Autowired
	@Qualifier("userService")
	public UserService userService;

	@ModelAttribute
	public void currentPage(WebRequest request, Model model) {
		model.addAttribute("currentPage", "customer");
	}

	@RequestMapping(value = { "/genBill", "/genBill/" }, method = RequestMethod.GET)
	public String genBill() {
		return "admin/generateBill";

	}
}
