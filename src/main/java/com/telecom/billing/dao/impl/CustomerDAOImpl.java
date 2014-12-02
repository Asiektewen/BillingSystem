/**
 * 
 */
package com.telecom.billing.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.dao.support.DataAccessUtils;
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
		String SQL_QUERY = " from Customer c where c.username=?";
		Query query = getCurrentSession().createQuery(SQL_QUERY);
		query.setParameter(0, username);
		List<Customer> list = query.list();

		if ((list != null) && (list.size() > 0)) {
			return true;
		}

		return false;
	}

	@Override
	public List<Customer> findAllCustomerByUser(int start, int size,
			String orderBy, String orderType, long userID, int type) {
		Query query = null;
		if (type == 1) {
			// salesRep
			query = getCurrentSession().createQuery(
					"FROM Customer  c where c.salesRepID= :salesRepId"
							+ orderType);
			query.setParameter("salesRepId", userID);
		} else {
			// admin
			query = getCurrentSession().createQuery("FROM Customer  c");
		}
		// query.setString("orderBy", orderBy);

		query.setFirstResult(start);
		query.setMaxResults(size);
		List<Customer> customerList = query.list();
		if (customerList != null) {
			return customerList;
		}
		return null;
	}

	@Override
	public Integer countAllCustomerByUser(long userID) {
		Query query = getCurrentSession().createQuery(
				"select count(*) from Customer c where c.salesRepID= ? ");
		query.setParameter(0, userID);

		return DataAccessUtils.intResult(query.list());
	}

	@Override
	public Integer countAllCustomerByAdmin() {
		Query query = getCurrentSession().createQuery(
				"select count(*) from Customer c ");

		return DataAccessUtils.intResult(query.list());
	}

	@Override
	public List<Customer> findAllCustomer() {
		Query query = getCurrentSession().createQuery(
				"select * from Customer c ");
		List<Customer> customerList = query.list();
		return customerList;
	}
}
