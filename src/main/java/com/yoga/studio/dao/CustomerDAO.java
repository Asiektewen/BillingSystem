package com.yoga.studio.dao;

import java.util.List;

import com.yoga.studio.model.Customer;



public interface CustomerDAO {
	/*
	 * CREATE and UPDATE
	 */
	public Customer saveCustomer(Customer customer); // create and update

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

	public List<Customer> listAdviseesByFacultyID(long id);


}
