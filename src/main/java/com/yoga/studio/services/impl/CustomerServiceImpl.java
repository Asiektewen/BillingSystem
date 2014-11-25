package com.yoga.studio.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoga.studio.dao.CustomerDAO;
import com.yoga.studio.model.Customer;
import com.yoga.studio.model.Faculty;
import com.yoga.studio.services.CustomerService;

import org.springframework.transaction.annotation.Propagation;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerDAO customerDAO;

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Customer saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customer.setJoinDate(new Date());
		return customerDAO.saveCustomer(customer);
		
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<Customer> listCustomers() {
		// TODO Auto-generated method stub
		return customerDAO.listCustomers();
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Customer getCustomer(Long id) {
		// TODO Auto-generated method stub
		return customerDAO.getCustomer(id);	
		}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void deleteCustomer(Long id) {
		// TODO Auto-generated method stub
		customerDAO.deleteCustomer(id);
		
	}

	@Override
	public Customer checkLogin(String userName, String userPassword) {
		// TODO Auto-generated method stub
		return customerDAO.checkLogin(userName, userPassword);
	}

	@Override
	public boolean isUsernameUnique(String username) {
		// TODO Auto-generated method stub
		return customerDAO.isUsernameUnique(username);
	}

	@Override
	 public List<Customer> listAdviseesByFaculty(Faculty faculty) {
	  return customerDAO.listAdviseesByFacultyID(faculty.getId());
	 }

}
