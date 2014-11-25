/**
 * 
 */
package com.telecom.billing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.telecom.billing.services.CustomerService;
import com.telecom.billing.services.UserService;
import com.telecom.billing.test.TestBase;

/**
 * @author zhangle
 *
 */

@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class ServiceImplTestBase extends TestBase {
	@Autowired
	@Qualifier("userService")
	protected UserService userService;
	@Autowired
	@Qualifier("customerService")
	protected CustomerService customerService;
}
