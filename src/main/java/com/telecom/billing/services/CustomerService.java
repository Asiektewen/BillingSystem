/**
 * 
 */
package com.telecom.billing.services;

import java.util.List;

import com.telecom.billing.model.Customer;

/**
 * @author zhangle
 *
 */
public interface CustomerService extends GenericService<Customer> {
	public Boolean doesNameExist(String username);

	public Integer countAllCustomerByUser(long userID);

	public List<Customer> findAllCustomerByUser(int start, int size,
			String orderBy, String orderType, long userID, int type);

	public Integer countAllCustomerByAdmin();
}
