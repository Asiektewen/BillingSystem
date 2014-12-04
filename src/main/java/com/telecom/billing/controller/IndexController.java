/**
 * 
 */
package com.telecom.billing.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import com.telecom.billing.model.User;
import com.telecom.billing.services.CountryInfoService;
import com.telecom.billing.services.CustomerService;
import com.telecom.billing.services.FileService;
import com.telecom.billing.services.ServiceInfoService;
import com.telecom.billing.services.UserService;
import com.telecom.billing.web.FireAuthority;

/**
 * @author zhangle
 *
 */
@Controller
@SessionAttributes({ "user" })
public class IndexController {
	@Autowired
	@Qualifier("userService")
	protected UserService userService;
	@Autowired
	@Qualifier("customerService")
	protected CustomerService customerService;

	@Autowired
	@Qualifier("fileService")
	protected FileService fileService;
	@Autowired
	@Qualifier("serviceInfoService")
	protected ServiceInfoService serviceInfoService;
	@Autowired
	@Qualifier("countryInfoService")
	protected CountryInfoService countryInfoService;
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
			Integer sCount = userService.countAllUserNotAdmin();
			Integer cCount = customerService.countAllCustomerByAdmin();
			Integer rateCount = serviceInfoService.countAll();
			Integer uCount = countryInfoService.countAll();
			map.addAttribute("sCount", sCount);
			map.addAttribute("cCount", cCount);
			map.addAttribute("rateCount", rateCount);
			map.addAttribute("uCount", uCount);
			return "admin/home";
		}

	}
}
