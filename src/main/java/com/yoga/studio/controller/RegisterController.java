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
import com.yoga.studio.model.User;
import com.yoga.studio.model.Faculty;
import com.yoga.studio.services.CustomerService;
import com.yoga.studio.services.FacultyService;
import com.yoga.studio.services.UserService;


/**
 * @author Abdul Saeed Ahmadi
 * Handles requests for the application home page.
 * 
 */
@Controller
@SessionAttributes({"user"})

public class RegisterController {
	
	
	@Autowired
	CustomerService customerService;
	@Autowired
	FacultyService facultyService;
	@Autowired
	UserService userService;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/listCustomers", method = RequestMethod.GET)
	public String listCustomers(Locale locale, Map<String, Object> map) {
		List<Customer> customers = customerService.listCustomers();
		map.put("customers", customers);
		
		return "listCustomers";
	}
	
// Admin Actor
	@RequestMapping(value = { "/editCustomer/{customerId}" }, method = RequestMethod.GET)
	public String editCustomer(@PathVariable Long customerId, Locale locale,
			Map<String, Object> map) {
		Customer customer = customerService.getCustomer(customerId);
		map.put("customer", customer);
		return "faculty/edit";
	}

	@RequestMapping(value = { "/faculty/editCustomer" }, method = RequestMethod.POST)
	public String adminSaveCustomer(
			@ModelAttribute("customer") Customer customer) {
		// Customer customer = customerService.getCustomer(customerId);
		// map.put("customer", customer);
		customerService.saveCustomer(customer);
		return "redirect:/listCustomers";	
	}
	
	//Customer Actor
	@RequestMapping(value = {"/editProfile"}, method = RequestMethod.GET)
	public String editCustomer1(Locale locale,@ModelAttribute("user") User user) {
		
		if(user.isStatus())
			return "edit";
		
		return "home";
	}
	
	
	
	@RequestMapping(value = {"/signup"}, method = RequestMethod.GET)
	public String signUp(Locale locale, Map<String, Object> map) {
		
		return "signup";
	}
	// User Register & Update
	@RequestMapping(value = {"/saveCustomer","editCustomer/saveCustomer"}, method = RequestMethod.POST)
	public String addCustomer(@ModelAttribute("user") User user,
			BindingResult result,ModelMap map) {
		//Profile Edit for currently logged in customer
		if(user.getRole().equals("customer") ){
			
			customerService.saveCustomer(new Customer(user));
			//map.put("", "");
		}
		//Account creation for new customer and automatic login
		else if (user.getRole().equals("default")){
			
			if(customerService.isUsernameUnique(user.getUsername())){
				user.setRole("customer");
				user = new Customer(user);
				user = customerService.saveCustomer((Customer)user);
				System.out.println(user.getId());
				user.setStatus(true);
				map.put("user", user);
			}
			else{
				map.addAttribute("status", "false");
				return "signup";
				
			}
		}
		//Profile Edit for currently logged in faculty
		else if (user.getRole().equals("faculty") ){
			facultyService.update(new Faculty(user));
		}
		else if (user.getRole().equals("admin") ){
			userService.saveUser(user);
		}
		
		
		return "redirect:/";
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        // You can register other Custom Editors with the WebDataBinder, like CustomNumberEditor for Integers and Longs, or StringTrimmerEditor for Strings
    }  
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, @ModelAttribute("user") User user) {
		if(!user.isStatus()){
			return "login";
			}
		
		return "home";
	}
	
	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	public String loginCheck(@ModelAttribute("user") User user,
			BindingResult result, ModelMap map) {
		
		User authUser = userService.checkLogin(user.getUsername(), user.getPassword());;
			

		
		if(authUser!=null){
			
			if(authUser.getRole().equals("customer"))
				authUser = (Customer) authUser;
			else if (authUser.getRole().equals("faculty"))
				authUser = (Faculty) authUser;
			

			authUser.setStatus(true);			
			map.addAttribute("user",authUser);
			return "home";
		}
		
		else{
			map.addAttribute("status", "false");
			return "login";
			}
		
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Locale locale, ModelMap map) {
			User newUser = new User();
			newUser.setRole("default");
			map.put("user",newUser);
			return "login";
			
		
	}
	
	@RequestMapping(value = "/deleteCustomer/{customerId}", method = RequestMethod.GET)
	public String deleteCustomer(@PathVariable Long customerId, Locale locale) {
		customerService.deleteCustomer(customerId);
		return "redirect:/listCustomers";	
		}
	
}
