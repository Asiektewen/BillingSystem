package com.telecom.billing.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.telecom.billing.model.User;

/**
 * @author Abdul Saeed Ahmadi
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({"user"})
public class HomeController {
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, ModelMap map) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		//change role arcording to logged in user
		//	admin / customer / faculty
			if(!map.containsAttribute("user")){
				User newUser = new User();
				newUser.setRole("default");
			    //newUser.setRole("default");
				map.addAttribute("user", newUser);
			}
			
		//request.getSession().setAttribute("role", "admin");
			map.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	
}
