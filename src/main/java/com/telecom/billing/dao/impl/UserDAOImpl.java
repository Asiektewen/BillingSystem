/**
 * 
 */
package com.telecom.billing.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import com.telecom.billing.common.SysConstant;
import com.telecom.billing.dao.UserDAO;
import com.telecom.billing.model.User;

/**
 * @author zhangle
 *
 */
@Repository("userDAO")
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.telecom.billing.dao.UserDAO#getUserByUsername(java.lang.String)
	 */
	@Override
	public User getUserByUsername(String username) {
		String sql = "FROM User  U WHERE U.username= ? ";
		Query query = getCurrentSession().createQuery(sql);
		query.setParameter(0, username);
		List<User> results = query.list();
		if (results != null && results.size() > 0) {
			return results.get(0);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.telecom.billing.dao.UserDAO#doesNameExit(java.lang.String)
	 */
	@Override
	public Boolean doesNameExit(String username) {
		String sql = "FROM User  U WHERE U.username= ? ";
		Query query = getCurrentSession().createQuery(sql);
		query.setParameter(0, username);
		List<User> results = query.list();
		if (results != null && results.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<User> findUsersWithoutAdmin(int start, int size,
			String orderBy, String orderType) {
		Query query = getCurrentSession().createQuery("FROM User u ");
		// + "' order by u.joinDate");
		// query.setParameter(0, SysConstant.ROLE_ADMIN);
		// query.setString("orderBy", orderBy);
		query.setFirstResult(start);
		query.setMaxResults(size);
		List<User> userList = query.list();
		if (userList != null) {
			return userList;
		}
		return null;
	}

	@Override
	public Integer countAllUserNotAdmin() {
		return DataAccessUtils.intResult(getCurrentSession().createQuery(
				"select count(*) from User u where u.role != '"
						+ SysConstant.ROLE_ADMIN + "'").list());
	}

}
