package com.yoga.studio.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yoga.studio.model.Customer;
import com.yoga.studio.model.Section;
import com.yoga.studio.services.CustomerService;


/**
 * @author Abdul Saeed Ahmadi
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({"user"})

public class CustomerController {
	
	
	@Autowired
	CustomerService customerService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/listWaivers", method = RequestMethod.GET)
	public String listWaivers(Locale locale, Map<String, Object> map) {
			
		return "listWaivers";
	}
	
	@RequestMapping(value = "/listCourseHistory", method = RequestMethod.GET)
	public String listCourseHistory(Locale locale, Map<String, Object> map) {
			
		return "listCourseHistory";
	}
	
	
	
	
}
