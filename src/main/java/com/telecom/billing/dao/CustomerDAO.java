/**
 * 
 */
package com.telecom.billing.dao;

import com.telecom.billing.model.Customer;

/**
 * @author zhangle
 *
 */
public interface CustomerDAO extends GenericDAO<Customer> {
	public Boolean doesNameExit(String username);
}
