/**
 * 
 */
package com.telecom.billing.service;

import java.io.File;

import org.junit.Test;

/**
 * @author zhangle
 *
 */
public class UserServiceImplTest extends ServiceImplTestBase {

	// @Test
	// @Transactional
	// public void testSaveUser() {
	// User user = createUser();
	// user = userService.save(user);
	// User newUser = userService.findOne(user.getId());
	// assertNotNull(user.getId());
	// assertEquals(user.getId(), newUser.getId());
	// userService.deleteById(newUser.getId());
	// User user3 = userService.findOne(user.getId());
	// assertNull(user3);
	// }

	public void testRateHistory() throws Exception {
		File ratefile = new File("C:\\input\\Rates_20130901.xls");
		fileService.readRateFile(ratefile);

	}

	@Test
	public void processRateUpdate() {
		fileService.processRateUpdate();
	}

	// @Ignore
	// @Transactional
	// public void testListUser() {
	// User admin = getAdmin();
	// admin = userService.save(admin);
	// for (int i = 0; i < 50; i++) {
	// User user = createUser();
	// user = userService.save(user);
	// }
	// List<User> userList = userService.findAll();
	// // assertEquals(userList.size(), 51);
	// List<User> userNotAdminList = userService.findUsersWithoutAdmin(0, 10,
	// "joinDate", "asc");
	// assertEquals(userNotAdminList.size(), 10);
	// Integer allNotAdmin = userService.countAllUserNotAdmin();
	// assertEquals(userList.size(), allNotAdmin + 1);
	//
	// }

}
