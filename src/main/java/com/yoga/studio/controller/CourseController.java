//author: Tuan
package com.yoga.studio.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yoga.studio.model.Course;
import com.yoga.studio.model.Customer;
import com.yoga.studio.model.Section;
import com.yoga.studio.services.CourseService;
import com.yoga.studio.services.CustomerService;


/**
 * @author Abdul Saeed Ahmadi
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("user")

public class CourseController {	
	@Autowired
	CourseService courseService;
	
	@RequestMapping(value = "/addPrerequisite/{courseId}/{prerequisiteId}", method = RequestMethod.GET)	
	public String addPrerequisite(@PathVariable Long courseId, @PathVariable Long prerequisiteId, Locale locale, Map<String, Object> map)
	{
		Course course = courseService.getCourse(courseId);
		System.out.printf("111 courseId = %d, title = %s, prerequisiteId = %d, \n", course.getId() , course.getTitle(), prerequisiteId);
		if(course != null)
		{		
			System.out.printf(" before size = %d \n" , course.getPrerequisites().size());
			Course newPrerequisite = courseService.getCourse(prerequisiteId);
			course.getPrerequisites().add(newPrerequisite);
			System.out.printf(" after size= %d , course id= %d, newPrerequisiteId = %d \n" , 
					course.getPrerequisites().size(), course.getId(), newPrerequisite.getId());
			
			courseService.saveCourse(course);
			
					
			map.put("course", course);
		}
		List<Course> remainPrerequisites =courseService.getRemainPrerequisites (courseId);
		map.put("remainPrerequisites", remainPrerequisites);
		
		return "editPrerequisite";
	}	
	@RequestMapping(value = "/editPrerequisite/{courseId}", method = RequestMethod.GET)	
	public String editPrerequisite(@PathVariable Long courseId, Locale locale, Map<String, Object> map)
	{
		Course course = courseService.getCourse(courseId);
		//System.out.printf(" courseId = %d \n" , courseId);
		if(course != null)
		{		
			map.put("course", course);
		}
		
		List<Course> remainPrerequisites =courseService.getRemainPrerequisites (courseId);
		map.put("remainPrerequisites", remainPrerequisites);

		return "editPrerequisite";
	}		

	@RequestMapping(value = "/listCourses", method = RequestMethod.GET)
	public String showCourses(Locale locale, Map<String, Object> map) {
		List<Course> courses = courseService.listCourses();		
		//List<Course> prerequisites = courses.get(2);

		map.put("courses", courses);
		return "listCourses";
	}
//	@RequestMapping(value = "/Courses", method = RequestMethod.GET)
//	public Map Courses(Locale locale, Map<String, Object> map) {
//		List<Course> courses = courseService.listCourses();		
//		//List<Course> prerequisites = courses.get(2);
//
//		map.put("courses", courses);
//		return map;
//	}
	@RequestMapping(value = "/editcourse", method = RequestMethod.POST)
	public String editcourse(@RequestParam("courseId") String courseId, @RequestParam("title") String title)
	{		
		Course course = courseService.getCourse( Long.parseLong(courseId));
		if(course != null)
		{
			course.setTitle(title);
			courseService.saveCourse(course);	
		}
		return "redirect:/listCourses";
	}	
//	@RequestMapping(value = {"/editCustomer/{customerId}"}, method = RequestMethod.GET)
//	public String editCustomer(@PathVariable Long customerId, Locale locale, Map<String, Object> map) {
//		Customer customer = customerService.getCustomer(customerId);
//		map.put("customer", customer);
//		return "edit";
//	}
	
	@RequestMapping(value = "/deletecourse", method = RequestMethod.POST)
	public String deleteCourse(@RequestParam("courseId") String courseId, @RequestParam("title") String title) 
	{
		courseService.deleteCourse(Long.parseLong(courseId));
		return "redirect:/listCourses";
	}	
	@RequestMapping(value = "/addcourse", method = RequestMethod.POST)
	public String addcourse(@RequestParam("title") String title)
	{
		Course course = new Course();
		course.setTitle(title);	
		courseService.saveCourse(course);	

		return "redirect:listCourses";
	}	
	@RequestMapping(value = "/deleteprerequisite", method = RequestMethod.POST)
	public String deleteprerequisite(@RequestParam("courseId") String courseId, 
			@RequestParam("prerequisiteId") String prerequisiteId,
			Locale locale, 
			Map<String, Object> map) 
	{
		//remove prerequisite
		Course course = courseService.getCourse( Long.parseLong(courseId));
		Course prerequisite = courseService.getCourse( Long.parseLong(prerequisiteId));
		
		course.removePrerequisite(prerequisite);

		courseService.saveCourse(course);
		
		//update list
		if(course != null)
		{		
			map.put("course", course);
			List<Course> remainPrerequisites =courseService.getRemainPrerequisites (course.getId());
			map.put("remainPrerequisites", remainPrerequisites);		
		}
		return "editPrerequisite";
	}	
}
