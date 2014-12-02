/**
 * 
 */
package com.telecom.billing.data;

import org.junit.Before;
import org.junit.Test;

import com.telecom.billing.model.User;
import com.telecom.billing.service.ServiceImplTestBase;

/**
 * @author zhangle
 *
 */
public class GenerateIntegrationData extends ServiceImplTestBase {

	@Before
	public void setUp() {

	}

	@Test
	public void testAllData() {
		generateAdminAndSales();
	}

	public void generateAdminAndSales() {
		User admin = getAdmin();
		admin = userService.save(admin);
		// // for (int i = 0; i < 10; i++) {
		// // User user = createUser();
		// // user = userService.save(user);
		// // }
		// List<User> userList = userService.findAll();
		// // assertEquals(userList.size(), 51);
		// List<User> userNotAdminList = userService.findUsersWithoutAdmin(0,
		// 10,
		// "joinDate", "asc");
		// // assertEquals(userNotAdminList.size(), 10);
		// Integer allNotAdmin = userService.countAllUserNotAdmin();
		// // assertEquals(userList.size(), allNotAdmin + 1);
	}
}
