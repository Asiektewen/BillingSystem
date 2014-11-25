package com.telecom.billing.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.telecom.billing.dao.ProductDAO;
import com.telecom.billing.model.Product;
import com.telecom.billing.model.Section;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void saveProduct(Product product) {
		// TODO Auto-generated method stub

		sessionFactory.getCurrentSession().merge(product);

	}

	@Override
	public List<Product> listProducts() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createCriteria(Product.class)
				.list();
	}

	// tuandang
	@Override
	public List<Product> searchProducts(String searchText) {
		searchText = searchText.trim().toLowerCase();
		String queryString = String.format(
				"from Product where name like '%%%s%%' or unit like '%%%s%%' ",
				searchText, searchText);
		return sessionFactory.getCurrentSession().createQuery(queryString)
				.list();
	}

	@Override
	public Product getProduct(Long id) {
		// TODO Auto-generated method stub
		return (Product) sessionFactory.getCurrentSession().get(Product.class,
				id);
	}

	@Override
	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		Product product = getProduct(id);
		if (product != null)
			sessionFactory.getCurrentSession().delete(product);

	}

	@Override
	public boolean checkLogin(String userName, String userPassword) {
		System.out.println("In Check login");
		Session session = sessionFactory.openSession();
		boolean userFound = false;
		// Query using Hibernate Query Language
		String SQL_QUERY = " from Product as c where c.username=? and c.password=?";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter(0, userName);
		query.setParameter(1, userPassword);
		List list = query.list();

		if ((list != null) && (list.size() > 0)) {
			userFound = true;
		}

		session.close();
		return userFound;
	}

}
