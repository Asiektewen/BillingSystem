package com.telecom.billing.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.telecom.billing.dao.CustomerDAO;
import com.telecom.billing.model.Customer;
import com.telecom.billing.model.Section;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Customer saveCustomer(Customer customer) {
		// TODO Auto-generated method stub

		return (Customer) sessionFactory.getCurrentSession().merge(customer);

	}

	@Override
	public List<Customer> listCustomers() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession()
				.createCriteria(Customer.class).list();
	}

	@Override
	public Customer getCustomer(Long id) {
		// TODO Auto-generated method stub
		return (Customer) sessionFactory.getCurrentSession().get(
				Customer.class, id);
	}

	@Override
	public void deleteCustomer(Long id) {
		// TODO Auto-generated method stub
		Customer customer = getCustomer(id);
		if (customer != null)
			sessionFactory.getCurrentSession().delete(customer);

	}

	@Override
	public Customer checkLogin(String userName, String userPassword) {
		System.out.println("In Check login");
		Session session = sessionFactory.openSession();
		boolean userFound = false;
		// Query using Hibernate Query Language
		String SQL_QUERY = " from Customer as c where c.username=? and c.password=?";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter(0, userName);
		query.setParameter(1, userPassword);
		List list = query.list();

		if ((list != null) && (list.size() > 0)) {
			return (Customer) list.get(0);
		}

		session.close();
		return null;
	}

	@Override
	public boolean isUsernameUnique(String username) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		boolean userFound = false;
		// Query using Hibernate Query Language
		String SQL_QUERY = " from User as c where c.username=?";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter(0, username);
		List list = query.list();
		session.close();

		if ((list != null) && (list.size() > 0)) {
			return false;
		}

		return true;
	}

	@Override
	public List<Customer> listAdviseesByFacultyID(long id) {
		String sql = "FROM Customer AS C WHERE C.faculty.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter(0, id);
		List<Customer> results = (List<Customer>) query.list();
		return results;
	}

}
