/**
 * 
 */
package com.yoga.studio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.yoga.studio.services.CourseService;
import com.yoga.studio.services.CustomerService;
import com.yoga.studio.services.FacultyService;
import com.yoga.studio.services.SectionService;
import com.yoga.studio.services.WaiverService;
import com.yoga.studio.test.TestBase;

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
