/**
 * 
 */
package com.telecom.billing.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.telecom.billing.model.Faculty;

/**
 * @author zhangle
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/root-context.xml",
		"classpath:spring/appServlet/servlet-context.xml" })
public class TestBase {
	private static Faculty globalFaculty;
	private static Faculty admin;

	//
	// public static Faculty getGlobalFaculty() {
	// if (globalFaculty == null) {
	// globalFaculty = createOneNewFaculty();
	// }
	// return globalFaculty;
	// }
	//
	// public static Faculty getAdmin() {
	// if (admin == null) {
	// admin = createOneNewFaculty();
	// admin.setRole("admin");
	// }
	// return admin;
	//
	// }

	@Test
	public void testEmpty() {
		// do nothing
	}

}
