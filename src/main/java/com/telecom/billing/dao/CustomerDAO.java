/**
 * 
 */
package com.telecom.billing.dao;

import java.util.List;

import com.telecom.billing.model.Customer;

/**
 * @author zhangle
 *
 */
public interface CustomerDAO extends GenericDAO<Customer> {
	public Boolean doesNameExit(String username);

	public Integer countAllCustomerByUser(long userID);

	public Integer countAllCustomerByAdmin();

	public List<Customer> findAllCustomerByUser(int start, int size,
			String orderBy, String orderType, long userID, int type);

	public List<Customer>  findAllCustomer();
}
