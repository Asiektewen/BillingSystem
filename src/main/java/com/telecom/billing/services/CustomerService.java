package com.telecom.billing.services;

import java.util.List;

import com.telecom.billing.model.Customer;
import com.telecom.billing.model.Faculty;


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
