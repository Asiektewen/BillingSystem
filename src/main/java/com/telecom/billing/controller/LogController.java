/**
 * 
 */
package com.telecom.billing.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zhangle
 *
 */
@Controller
@RequestMapping("/admin")
public class LogController {
	@RequestMapping(value = { "/login", "/login/" }, method = RequestMethod.GET)
	public String login(Locale locale, ModelMap map) {
		return "admin/login";
	}

	@RequestMapping(value = { "/logout", "/logout/" }, method = RequestMethod.GET)
	public String logout(Locale locale, ModelMap map) {
		return "redirect:login";
	}
}
