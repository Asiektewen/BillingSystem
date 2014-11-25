/**
 * 
 */
package com.telecom.billing.services;

import com.telecom.billing.model.Customer;

/**
 * @author zhangle
 *
 */
public interface CustomerService extends GenericService<Customer> {
	public Boolean doesNameExist(String username);
}
