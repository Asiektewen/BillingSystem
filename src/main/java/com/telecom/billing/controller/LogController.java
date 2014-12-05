/**
 * 
 */
package com.telecom.billing.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.telecom.billing.model.User;
import com.telecom.billing.services.UserService;

/**
 * @author zhangle
 *
 */
@Controller
@RequestMapping("/admin")
// @SessionAttributes({ "user" })
public class LogController {
	private static final Logger logger = LoggerFactory
			.getLogger(LogController.class);
	@Autowired
	@Qualifier("userService")
	public UserService userService;

	@RequestMapping(value = { "/login", "/login/" }, method = RequestMethod.GET)
	public String login(Locale locale, ModelMap map, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "admin/login";
		} else {
			return "redirect:/admin/";
		}

	}

	@RequestMapping(value = { "/login", "/login/" }, method = RequestMethod.POST)
	public String login(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			// @RequestParam(value = "remember") String remember,
			Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		User user = userService.getUserByUsername(username);
		if (user != null && user.getPassword().equalsIgnoreCase(password)) {
			// model.put("user", user);
			request.getSession().setAttribute("user", user);
			StringBuilder sb = new StringBuilder();
			sb.append(request.getContextPath());
			sb.append("/admin/");
			response.sendRedirect(sb.toString());
			return null;
		} else {
			model.put("error", "true");
			return "admin/login";
		}

	}

	@RequestMapping(value = { "/logout", "/logout/" }, method = RequestMethod.GET)
	public String logout(Locale locale, Map<String, Object> model,
			@ModelAttribute("user") User user, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.getSession().setAttribute("user", null);
		StringBuilder sb = new StringBuilder();
		sb.append(request.getContextPath());
		sb.append("/admin/login/");
		response.sendRedirect(sb.toString());
		return null;
	}
}
