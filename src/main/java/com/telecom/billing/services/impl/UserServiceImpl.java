/**
 * 
 */
package com.telecom.billing.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.telecom.billing.dao.GenericDAO;
import com.telecom.billing.dao.UserDAO;
import com.telecom.billing.model.User;
import com.telecom.billing.services.UserService;

/**
 * @author zhangle
 *
 */
@Service("userService")
public class UserServiceImpl extends GenericServiceImpl<User> implements
		UserService {
	@Autowired
	@Qualifier("userDAO")
	public UserDAO userDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.telecom.billing.services.UserService#getUserByUsername(java.lang.
	 * String)
	 */
	@Override
	public User getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.telecom.billing.services.UserService#doesNameExit(java.lang.String)
	 */
	@Override
	public Boolean doesNameExit(String username) {
		return userDAO.doesNameExit(username);
	}

	@Override
	protected GenericDAO<User> getDAO() {
		return this.userDAO;
	}

	@Override
	public List<User> findUsersWithoutAdmin(int start, int size,
			String orderBy, String orderType) {
		return userDAO.findUsersWithoutAdmin(start, size, orderBy, orderType);
	}

	@Override
	public Integer countAllUserNotAdmin() {
		return userDAO.countAllUserNotAdmin();
	}

}
