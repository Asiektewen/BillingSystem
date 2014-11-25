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
public class LoginController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String home(Locale locale, ModelMap map) {
		return "admin/login";
	}
}
