/**
 * 
 */
package com.telecom.billing.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.telecom.billing.model.Customer;
import com.telecom.billing.model.Faculty;

/**
 * @author zhangle
 *
 */
public class FacultyServiceImplTest extends ServiceImplTestBase {
	// @Autowired
	// @Qualifier("facultyService")
	// public FacultyService facultyService;

	@Test
	@Transactional
	public void testGenericOperationOnFaculty() {
		Faculty faculty = createOneNewFaculty();
		faculty = facultyService.save(faculty);
		Faculty newFaculty = facultyService.findOne(faculty.getId());
		assertNotNull(faculty.getId());
		assertNotNull(newFaculty);
		int count = facultyService.countAll();
		assertNotEquals(count, 0);
		facultyService.delete(newFaculty);
		Faculty retreiveFaculty = facultyService.findOne(newFaculty.getId());
		assertNull(retreiveFaculty);
	}

	@Test
	@Transactional
	public void testGetAdvisees() {
		Faculty faculty = createOneNewFaculty();
		Customer customer = createOneNewCustomer();
		customer.setFaculty(faculty);
		customer = customerService.saveCustomer(customer);
		List<Customer> adviseeList = customerService
				.listAdviseesByFaculty(customer.getFaculty());
		assertNotEquals(adviseeList.size(), 0);

	}
}
