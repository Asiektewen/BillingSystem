package com.telecom.billing.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/admin/callDetails")
public class CallDetailsController {
	private static final Logger logger = LoggerFactory
			.getLogger(CallDetailsController.class);
	@Autowired
	@Qualifier("userService")
	public UserService userService;

	@ModelAttribute
	public void currentPage(WebRequest request, Model model) {
		model.addAttribute("currentPage", "callDetails");
	}

	@RequestMapping(value = { "/upload", "/upload/" }, method = RequestMethod.GET)
	public String uploadCallDetailsPage(HttpServletRequest request, Model model) {
		String message = (String) request.getSession().getAttribute(
				"uploadMessage");
		model.addAttribute("uploadMessage", message);
		return "admin/callDetailsProcess";

	}
}
