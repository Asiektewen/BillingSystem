/**
 * 
 */
package com.telecom.billing.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.telecom.billing.model.User;

/**
 * @author zhangle
 *
 */
public class UserDAOImplTest extends DAOImplTestBase {
	@Autowired
	@Qualifier("userDAO")
	private UserDAO userDAO;

	@Test
	@Transactional
	public void testUserDAO() {
//		User user = userDAO.save(createUser());
//		User newUser = userDAO.findOne(user.getId());
//		assertNotNull(user.getId());
//		assertNotNull(newUser);
//		User user3 = userDAO.getUserByUsername(user.getUsername());
//		Boolean doesExist = userDAO.doesNameExit(user.getUsername());
//		assertEquals(user3.getUsername(), user.getUsername());
//		assertEquals(doesExist.booleanValue(), true);
		
	}
}
