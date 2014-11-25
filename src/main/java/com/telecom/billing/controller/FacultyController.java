/**
 * @author : 
 */
package com.telecom.billing.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.telecom.billing.model.Faculty;
import com.telecom.billing.model.Section;
import com.telecom.billing.services.FacultyService;
import com.telecom.billing.services.SectionService;
import com.telecom.billing.web.FireAuthority;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("user")
public class FacultyController {
	@Autowired
	FacultyService facultyService;

	@Autowired
	private SectionService sectionService;

	/**
	 * List sections to Faculty
	 * 
	 * @param map
	 * @return pageName
	 */
	@FireAuthority
	@RequestMapping(value = "/faculty/listSections", method = RequestMethod.GET)
	public String listSections(Map<String, Object> map) {
		List<Section> sections = sectionService.listSections();
		map.put("sections", sections);

		return "faculty/listSections";
	}

	@RequestMapping(value = "/listFaculty", method = RequestMethod.GET)
	public String showFaculty(Locale locale, Map<String, Object> map) {
		List<Faculty> faculties = facultyService.findAll();
		map.put("faculties", faculties);
		return "listFaculty";
	}

	@RequestMapping(value = "/editFaculty", method = RequestMethod.GET)
	public String editfaculty(@RequestParam("facultyId") String facultyId,
			@RequestParam("full_name") String full_name,
			@RequestParam("contact_information") String contact_information,
			@RequestParam("username") String username,
			@RequestParam("password") String password) {
		Faculty faculty = facultyService.findOne(Long.parseLong(facultyId));
		if (faculty != null) {
			faculty.setFullName(full_name);
			faculty.setContactInformation(contact_information);
			faculty.setUsername(username);
			faculty.setPassword(password);

			facultyService.save(faculty);
		}
		return "redirect:/listFaculty";
	}

	//

	@RequestMapping(value = "/deletefaculty", method = RequestMethod.POST)
	// public String deleteFaculty(@PathVariable Long facultyId, Locale locale,
	// Map<String, Object> map)
	public String deleteFaculty(@RequestParam("facultyId") String facultyId,
			@RequestParam("title") String title) {
		// System.out.printf("facultyId = %d ", facultyId);
		facultyService.deleteById(Long.parseLong(facultyId));
		return "redirect:/listFaculty";
	}

	//
	@RequestMapping(value = "/addfaculty", method = RequestMethod.POST)
	public String addFaculty(@ModelAttribute("faculty") Faculty faculty,
			BindingResult result) {
		facultyService.save(faculty);
		return "redirect:/listFaculty";

	}
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        // You can register other Custom Editors with the WebDataBinder, like CustomNumberEditor for Integers and Longs, or StringTrimmerEditor for Strings
    } 

}
