/**
 * 
 */
package com.telecom.billing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.telecom.billing.services.CourseService;
import com.telecom.billing.services.CustomerService;
import com.telecom.billing.services.FacultyService;
import com.telecom.billing.services.SectionService;
import com.telecom.billing.services.WaiverService;
import com.telecom.billing.test.TestBase;

/**
 * @author zhangle
 *
 */

@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class ServiceImplTestBase extends TestBase {
	@Autowired
	@Qualifier("waiverService")
	protected WaiverService waiverService;
	@Autowired
	@Qualifier("facultyService")
	protected FacultyService facultyService;
	@Autowired
	protected CustomerService customerService;
	@Autowired
	protected CourseService courseService;
	@Autowired
	protected SectionService sectionService;
}
