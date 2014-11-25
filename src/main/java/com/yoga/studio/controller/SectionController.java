package com.yoga.studio.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.yoga.studio.model.Course;
import com.yoga.studio.model.Customer;
import com.yoga.studio.model.Enrollment;
import com.yoga.studio.model.Faculty;
import com.yoga.studio.model.Section;
import com.yoga.studio.services.CourseService;
import com.yoga.studio.services.CustomerService;
import com.yoga.studio.services.EnrollmentService;
import com.yoga.studio.services.FacultyService;
import com.yoga.studio.services.SectionService;

/**
 * @author Abdul Saeed Ahmadi Handles requests for the application home page.
 */
@Controller
@SessionAttributes({ "user", "enrollMessage" })
public class SectionController {

	@Autowired
	SectionService sectionService;

	@Autowired
	CustomerService customerService;

	@Autowired
	EnrollmentService enrollmentService;

	@Autowired
	CourseService courseService;

	@Autowired
	@Qualifier("facultyService")
	FacultyService facultyService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/currentSections", method = RequestMethod.GET)
	public String listSections(Locale locale, Map<String, Object> map) {
		List<Section> sections = sectionService.listSections();
		map.put("sections", sections);

		return "listSections";
	}

	@RequestMapping(value = "/manageSections", method = RequestMethod.GET)
	public String manageSections(Locale locale, Map<String, Object> map) {
		List<Section> sections = sectionService.listSections();
		map.put("sections", sections);
		map.put("section", new Section());

		map.put("listCourses", courseService.listCourses());
		map.put("listFaculty", facultyService.findAll());

		return "manageSections";
	}

	@RequestMapping(value = "/courseList", method = RequestMethod.GET)
	public String showCourses(Locale locale, Map<String, Object> map) {
		List<Section> courses = sectionService.listSections();
		map.put("courses", courses);
		map.put("listCourses", courseService.listCourses());
		return "manageSections";
	}

	@RequestMapping(value = "/Sections")
	public ModelAndView addUserPage() {
		ModelAndView mav = new ModelAndView("manageSections");
		List<Course> courses = courseService.listCourses();
		mav.addObject("courses", courses);
		return mav;
	}

	@RequestMapping(value = "/listSectionsEnroll", method = RequestMethod.GET)
	public String listSectionsEnroll(Locale locale, ModelMap map,
			@ModelAttribute("user") Customer customer) {

		List<Section> sections = sectionService.listCurrentSections(customer
				.getId());

		map.put("sections", sections);

		return "listSectionsEnroll";
	}

	@RequestMapping(value = "/listSectionsWithDraw", method = RequestMethod.GET)
	public String listSectionsWithDraw(Locale locale, ModelMap map,
			@ModelAttribute("user") Customer customer) {
		Date now = new Date();
		List<Enrollment> withdrawEnrolls = enrollmentService.getEnrollments(
				customer.getId(), now);
		map.put("withdrawEnrolls", withdrawEnrolls);
		return "listSectionsWithDraw";
	}

	@RequestMapping(value = { "/deleteEnrollment/{enrollmentId}" }, method = RequestMethod.GET)
	public String editCustomer(@PathVariable Long enrollmentId, Locale locale) {
		Enrollment temp = enrollmentService.getEnrollment(enrollmentId);
		Section tempSection = temp.getSection();
		enrollmentService.deleteEnrollment(enrollmentId);
		List<Enrollment> tempEnrollments = enrollmentService.getEnrollment(
				tempSection.getId(), "waiting");
		if (tempEnrollments.size() > 0) {
			Collections.sort(tempEnrollments);
			temp = tempEnrollments.get(0);
			temp.setEnrollmentStatus("enrolled");
			enrollmentService.saveEnrollment(temp);
		} else {
			tempSection.setSeats(tempSection.getSeats() + 1);
			sectionService.saveSection(tempSection);
		}

		return "redirect:/listSectionsWithDraw";
	}

	@RequestMapping(value = "/enroll/{sectionId}", method = RequestMethod.GET)
	public String enrollSection(@PathVariable Long sectionId, Locale locale,
			@ModelAttribute("user") Customer customer1, ModelMap map) {
		customer1.getId();
		Customer customer = customerService.getCustomer((long) customer1
				.getId());
		System.out.println(customer1.getId() + "--" + customer.getId());
		Section section = sectionService.getSection(sectionId);
		// Checks if Customer is already Enrolled in this section
		for (int i = 0; i < section.getEnrollments().size(); i++) {

			if (section.getEnrollments().get(i).getCustomer().equals(customer)) {
				map.put("enrollMessage", "You are Already Enrolled to : "
						+ section.getCourse().getTitle());
				return "redirect:/listSectionsEnroll";
			}
		}
		// Checks course prerequisites
		if (!prerequisitesCheck(customer, section)) {
			map.put("enrollMessage",
					"You have not fullfilled the Prerequisites for : "
							+ section.getCourse().getTitle());
			return "redirect:/listSectionsEnroll";
		}

		Enrollment e = new Enrollment(customer, section);

		if (section.getSeats() != 0) {
			e.setEnrollmentStatus("Enrolled");
			section.setSeats(section.getSeats() - 1);
			map.put("enrollMessage", "You have Successfully Enrolled To : "
					+ section.getCourse().getTitle());
		} else {
			e.setEnrollmentStatus("Waiting");
			map.put("enrollMessage", "You are on the Waiting List for : "
					+ section.getCourse().getTitle());
		}
		// Assign Advisor if first enrollment
		if (customer.getFaculty() == null)
			assignAdvisor(customer);
		// save new enrollment
		enrollmentService.saveEnrollment(e);
		return "redirect:/listSectionsEnroll";
	}

	private void assignAdvisor(Customer customer) {
		// TODO Auto-generated method stub
		// FacultyService facultyService = new FacultyServiceImpl();
		List<Faculty> faculties = facultyService.findAll();
		int facultyId = (int) (Math.random() * faculties.size());
		System.out.println(facultyId + " " + faculties.size());
		customer.setFaculty(faculties.get(facultyId));
		customerService.saveCustomer(customer);

	}

	private boolean prerequisitesCheck(Customer customer, Section section) {
		if (section.getCourse().getPrerequisites().size() > 0) {
			List<Course> coursesTaken = new ArrayList<Course>();
			// Add Customer Courses to coursesTaken
			// System.out.println(customer.getFullName());
			for (int i = 0; i < customer.getEnrollments().size(); i++) {
				coursesTaken.add(customer.getEnrollments().get(i).getSection()
						.getCourse());
			}
			// Add Customer Waivers Courses to coursesTaken
			for (int i = 0; i < customer.getWaivers().size(); i++) {
				coursesTaken.add(customer.getWaivers().get(i).getCourse());
			}
			/*
			 * for(int j=0;j<coursesTaken.size();j++)
			 * System.out.println(coursesTaken.get(j).getTitle()); for(int
			 * j=0;j<section.getCourse().getPrerequisites().size();j++)
			 * System.out
			 * .println("R"+section.getCourse().getPrerequisites().get(
			 * j).getTitle());
			 */
			// Check courses taken against prerequisites
			for (int i = 0; i < section.getCourse().getPrerequisites().size(); i++) {
				if (!coursesTaken.contains(section.getCourse()
						.getPrerequisites().get(i)))
					return false;
			}
		}
		return true;

	}

	@RequestMapping(value = "/deletesection", method = RequestMethod.POST)
	// public String deleteSection(@PathVariable Long sectionId, Locale locale,
	// Map<String, Object> map)
	public String deleteSection(@RequestParam("sectionId") String sectionId) {
		// System.out.printf("sectionId = %d ", sectionId);
		sectionService.deleteSection(Long.parseLong(sectionId));
		return "redirect:/manageSections";
	}

	@RequestMapping(value = "/addSection", method = RequestMethod.POST)
	public String addSection(@ModelAttribute("section") Section section,
			BindingResult result, Map<String, Object> map) {
		// map.put("courses", courseService.listCourses());
		// System.out.println(session.getAttribute("course"));
		sectionService.saveSection(section);
		return "redirect:/manageSections";
	}

	@RequestMapping(value = "/editSection", method = RequestMethod.POST)
	public String editSection(@RequestParam("sectionId") Long sectionId,
			@RequestParam("seats") int sectionSeat,
			@RequestParam("course.id") String courseId,
			@RequestParam("faculty.id") String facultyId,
			@RequestParam("start_date") Date sDate,
			@RequestParam("end_date") Date eDate,
			@RequestParam("location") String loc) {

		Section section1 = sectionService.getSection(sectionId);

		Course course = courseService.getCourse(Long.parseLong(courseId));
		section1.setCourse(course);
		Faculty faculty = facultyService.findOne(Long.parseLong(facultyId));
		section1.setFaculty(faculty);
		section1.setStartDate(sDate);
		section1.setEndDate(eDate);
		section1.setSeats(sectionSeat);
		section1.setLocation(loc);
		sectionService.saveSection(section1);
		return "redirect:/manageSections";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		// You can register other Custom Editors with the WebDataBinder, like
		// CustomNumberEditor for Integers and Longs, or StringTrimmerEditor for
		// Strings
	}
}
