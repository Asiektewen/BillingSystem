/**
 * 
 */
package com.telecom.billing.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.telecom.billing.dao.CustomerDAO;
import com.telecom.billing.model.Customer;

/**
 * @author zhangle
 *
 */
@Repository("customerDAO")
public class CustomerDAOImpl extends GenericDAOImpl<Customer> implements
		CustomerDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.telecom.billing.dao.CustomerDAO#doesNameExit(java.lang.String)
	 */
	@Override
	public Boolean doesNameExit(String username) {
		// Session session = sessionFactory.openSession();
		// Query using Hibernate Query Language
		String SQL_QUERY = " from Customer as c where c.username=?";
		Query query = getCurrentSession().createQuery(SQL_QUERY);
		query.setParameter(0, username);
		List<Customer> list = query.list();

		if ((list != null) && (list.size() > 0)) {
			return true;
		}

		return false;
	}

}
