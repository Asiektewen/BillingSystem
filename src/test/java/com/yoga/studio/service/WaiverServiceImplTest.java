/**
 * 
 */
package com.yoga.studio.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.yoga.studio.model.Course;
import com.yoga.studio.model.Customer;
import com.yoga.studio.model.Faculty;
import com.yoga.studio.model.Waiver;

/**
 * @author zhangle
 *
 */
public class WaiverServiceImplTest extends ServiceImplTestBase {
	public Faculty currentFaculty;

	@Before
	public void setUp() {

	}

	@Test
	public void testSaveWaiver() {

		// Waiver waiver = createOneNewWaiver();
		System.out.println("123");
	}

	@Test
	// @Transactional
	public void testGetWaiverList() {
		Faculty faculty = createOneNewFaculty();
		faculty = facultyService.save(faculty);
		currentFaculty = faculty;
		Customer customer = createOneNewCustomer();
		customer.setFaculty(faculty);
		customer = customerService.saveCustomer(customer);
		Course course = createOneNewCourse();
		course = courseService.saveCourse(course);
		for (int i = 0; i < 5; i++) {
			Waiver w = new Waiver(customer, course);
			waiverService.save(w);
		}
		List<Waiver> waiverList = waiverService
				.getWaiverByFaculty(currentFaculty);
		assertEquals(waiverList.size(), 5);
	}
}
