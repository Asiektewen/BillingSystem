/**
 * 
 */
package com.telecom.billing.dao;

import java.util.List;

import com.telecom.billing.model.User;

/**
 * @author zhangle
 *
 */
public interface UserDAO extends GenericDAO<User> {
	public User getUserByUsername(String username);

	public Boolean doesNameExit(String username);

	public List<User> findUsersWithoutAdmin(int start, int size,
			String orderBy, String orderType);

	public Integer countAllUserNotAdmin();

}
