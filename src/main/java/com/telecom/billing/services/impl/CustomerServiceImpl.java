/**
 * 
 */
package com.telecom.billing.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.telecom.billing.dao.CustomerDAO;
import com.telecom.billing.dao.GenericDAO;
import com.telecom.billing.model.Customer;
import com.telecom.billing.services.CustomerService;

/**
 * @author zhangle
 *
 */
@Service("customerService")
public class CustomerServiceImpl extends GenericServiceImpl<Customer> implements
		CustomerService {
	@Autowired
	@Qualifier("customerDAO")
	public CustomerDAO customerDAO;

	@Override
	public Boolean doesNameExist(String username) {
		return customerDAO.doesNameExit(username);
	}

	@Override
	protected GenericDAO<Customer> getDAO() {
		return this.customerDAO;
	}

}
