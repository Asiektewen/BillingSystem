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
		File ratefile = new File("D:\\tmp\\input\\Calls_Dec2013.xls");
		fileService.readCallFile(ratefile);
		//fileService.readRateFile(ratefile);
		//fileService.processRateUpdate();

	}
	
	@Test
	public void processRateUpdate() throws Exception{
		//fileService.generateRateSheet("Rate_Spectra_USA_11-2014");
		
		// fileService.processBillBatch("BIll_11-2013");
		//fileService.generateMonthlyBills("BIll_11-2013");
		//fileService.processTrafficBatch("Traffic_Summary_for_11-2013");
		//fileService.generateTrafficSummary("Traffic_Summary_for_11-2013");
		fileService.generateMonthCommissions("monthly_commisison_for_11-2013");
		//fileService.processCommissionBatch("monthly_commisison_for_11-2013");
		
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
