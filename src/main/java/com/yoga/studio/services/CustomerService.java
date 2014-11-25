package com.yoga.studio.services;

import java.util.List;

import com.yoga.studio.model.Customer;
import com.yoga.studio.model.Faculty;


public interface CustomerService {
	/*
	 * CREATE and UPDATE 
	 */
	public Customer saveCustomer(Customer customer);

	/*
	 * READ
	 */
	public List<Customer> listCustomers();
	public Customer getCustomer(Long id);

	/*
	 * DELETE
	 */
	public void deleteCustomer(Long id);
    public Customer checkLogin(String userName, String userPassword);


	public boolean isUsernameUnique(String username);

	public List<Customer> listAdviseesByFaculty(Faculty faculty);

}
