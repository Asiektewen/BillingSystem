/**
 * 
 */
package com.telecom.billing.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.telecom.billing.model.Customer;
import com.telecom.billing.model.Faculty;
import com.telecom.billing.services.CustomerService;
import com.telecom.billing.web.FireAuthority;

/**
 * @author zhangle
 *
 */
@Controller
@RequestMapping("/advisees")
@SessionAttributes("user")
public class AdviseeController {
	@Autowired
	CustomerService customerService;

	/**
	 * List Advisees of particular faculty
	 * 
	 * @param facultyID
	 * @param model
	 * @return pageName
	 */
	@FireAuthority
	@RequestMapping(value = "/list/{facultyID}", method = RequestMethod.GET)
	public String listAdvisees(@PathVariable("facultyID") Long facultyID,
			Map<String, Object> model) {
		Faculty faculty = new Faculty();
		faculty.setId(facultyID);
		List<Customer> customerList = customerService
				.listAdviseesByFaculty(faculty);
		model.put("customers", customerList);
		// model.put("status", true);
		// model.put("objectid", waiverID.longValue());
		return "faculty/listAdvisees";
	}
}
