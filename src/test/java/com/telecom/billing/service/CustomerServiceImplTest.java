/**
 * 
 */
package com.telecom.billing.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.telecom.billing.model.Customer;
import com.telecom.billing.services.CustomerService;

/**
 * @author zhangle
 *
 */
public class CustomerServiceImplTest extends ServiceImplTestBase {
	@Autowired
	public CustomerService customerService;

	@Test
	@Transactional
	public void testSaveCustomer() {
		Customer c = createOneNewCustomer();
		c = customerService.saveCustomer(c);
		Customer c2 = customerService.getCustomer(c.getId());
		assertNotNull(c.getId());
		assertEquals(c.getId(), c2.getId());
		customerService.deleteCustomer(c2.getId());
		Customer c3 = customerService.getCustomer(c.getId());
		assertNull(c3);
	}

	@Test
	@Transactional
	public void testListCustomer() {
		Customer c = createOneNewCustomer();
		c = customerService.saveCustomer(c);
		List<Customer> cList = customerService.listCustomers();
		assertNotEquals(cList.size(), 0);
	}
}
