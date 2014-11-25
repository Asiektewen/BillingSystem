/**
 * 
 */
package com.yoga.studio.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Before;
import org.junit.Test;

import com.yoga.studio.model.Course;
import com.yoga.studio.model.Customer;
import com.yoga.studio.model.Faculty;
import com.yoga.studio.model.Section;
import com.yoga.studio.model.Waiver;
import com.yoga.studio.service.ServiceImplTestBase;

/**
 * @author zhangle
 *
 */
public class GenerateIntegrationData extends ServiceImplTestBase {
	public Faculty currentFaculty;

	@Before
	public void setUp() {

	}

	@Test
	public void testAllData() {
		Faculty faculty = getGlobalFaculty();
		Faculty admin = getAdmin();
		faculty = facultyService.save(faculty);
		admin = facultyService.save(admin);
		currentFaculty = faculty;
		// create 10 customer
		List<Customer> cList = new ArrayList<Customer>();
		for (int i = 0; i < 10; i++) {
			Customer customer = createOneNewCustomer();
			customer.setFaculty(faculty);
			customer = customerService.saveCustomer(customer);
			cList.add(customer);
		}
		// create one Course
		Course course = createOneNewCourse();
		course = courseService.saveCourse(course);
		// create ten Secions
		for (int i = 0; i < 10; i++) {
			Section s = new Section();
			s.setCourse(course);
			s.setFaculty(faculty);
			s.setEndDate(DateUtils.addDays(new Date(), 32));
			s.setLocation("White House!Obama's office!");
			s.setSeats(30);
			s.setStartDate(DateUtils.addDays(new Date(), 2));
			s = sectionService.saveSection(s);
		}
		for (int i = 0; i < 10; i++) {
			Waiver w = new Waiver(cList.get(i), course);
			waiverService.save(w);
		}
	}
}
