/**
 * 
 */
package com.yoga.studio.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yoga.studio.common.YogaConstant;
import com.yoga.studio.model.Course;
import com.yoga.studio.model.Customer;
import com.yoga.studio.model.Faculty;
import com.yoga.studio.model.Waiver;

/**
 * @author zhangle
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/root-context.xml",
		"classpath:spring/appServlet/servlet-context.xml" })
public class TestBase {
	private static Faculty globalFaculty;
	private static Faculty admin;

	public static Faculty getGlobalFaculty() {
		if (globalFaculty == null) {
			globalFaculty = createOneNewFaculty();
		}
		return globalFaculty;
	}

	public static Faculty getAdmin() {
		if (admin == null) {
			admin = createOneNewFaculty();
			admin.setRole("admin");
		}
		return admin;

	}

	protected Customer createOneNewCustomer() {
		Customer c = new Customer();
		c.setUsername(RandomData.randomName("Customer"));
		c.setPassword("111111");
		c.setFullName(RandomData.randomName("Bill"));
		c.setGender(YogaConstant.GENDER_MALE);
		c.setRole("customer");
		c.setDob(new Date());
		c.setEmail(c.getUsername() + "@usa.gov");
		c.setContactInformation("MUM");
		c.setJoinDate(new Date());
		return c;
	}

	protected Course createOneNewCourse() {
		Course course = new Course();
		course.setPrerequisites(null);
		course.setTitle(RandomData.randomName("Yoga"));
		course.setSections(null);
		return course;
	}

	protected static Faculty createOneNewFaculty() {
		Faculty faculty = new Faculty();
		faculty.setUsername(RandomData.randomName("Faculty"));
		faculty.setFullName(RandomData.randomName("Obama"));
		faculty.setGender(YogaConstant.GENDER_FEMALE);
		faculty.setJoinDate(new Date());
		faculty.setPassword("111111");
		faculty.setRole("faculty");
		faculty.setDescription("common faculty");
		return faculty;
	}

	protected Waiver createOneNewWaiver() {
		Faculty faculty = createOneNewFaculty();
		Customer customer = createOneNewCustomer();
		customer.setFaculty(faculty);
		Course course = createOneNewCourse();
		Waiver w = new Waiver(customer, course);
		return w;
	}

	@Test
	public void testEmpty() {
		// do nothing
	}

}
