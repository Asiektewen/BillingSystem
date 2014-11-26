/**
 * 
 */
package com.telecom.billing.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.telecom.billing.common.SysConstant;
import com.telecom.billing.model.User;

/**
 * @author zhangle
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/root-context.xml",
		"classpath:spring/appServlet/servlet-context.xml" })
public class TestBase {
	private static User admin;
	private static User globalSalesRep;

	public static User getGlobalSalesRep() {
		if (globalSalesRep == null) {
			globalSalesRep = createUser();
			globalSalesRep.setRole(SysConstant.ROLE_SALESREP);
		}
		return globalSalesRep;
	}

	public static User getAdmin() {
		if (admin == null) {
			admin = createUser();
			admin.setUsername("admin");
			admin.setPassword("admin");
			admin.setRole(SysConstant.ROLE_ADMIN);
		}
		return admin;

	}

	public static User createUser() {
		User user = new User();
		user.setUsername(RandomData.randomName("user"));
		user.setFullName(RandomData.randomName("Obama"));
		user.setAddress(RandomData.randomName("Address"));
		user.setDob(new Date());
		user.setEmail(RandomData.randomName("Email:"));
		user.setPhoneNumber(RandomData.randomName("641-"));
		user.setZipCode(RandomData.randomName("525"));
		user.setGender(SysConstant.GENDER_FEMALE);
		user.setJoinDate(new Date());
		user.setPassword("111111");
		user.setRole(SysConstant.ROLE_SALESREP);
		return user;
	}

	@Test
	public void testEmpty() {
		// do nothing
	}

}
