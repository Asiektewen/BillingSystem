/**
 * 
 */
package com.telecom.billing.services;

import java.util.List;

import com.telecom.billing.model.User;

/**
 * @author zhangle
 *
 */
public interface UserService extends GenericService<User> {
	public User getUserByUsername(String username);

	public Boolean doesNameExit(String username);

	public List<User> findUsersWithoutAdmin(int start, int size,
			String orderBy, String orderType);

	public Integer countAllUserNotAdmin();
}
