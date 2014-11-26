/**
 * 
 */
package com.telecom.billing.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import com.telecom.billing.model.User;
import com.telecom.billing.web.FireAuthority;

/**
 * @author zhangle
 *
 */
@Controller
@SessionAttributes({ "user" })
public class IndexController {
	@ModelAttribute
	public void currentPage(WebRequest request, Model model) {
		model.addAttribute("currentPage", "dashboard");
	}

	@FireAuthority
	@RequestMapping(value = { "/admin", "/admin/" }, method = RequestMethod.GET)
	public String home(Locale locale, ModelMap map, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/admin/login";
		} else {
			return "admin/home";
		}

	}
}
