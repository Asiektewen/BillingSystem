package com.yoga.studio.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yoga.studio.dao.ShoppingCartDAO;
import com.yoga.studio.model.ShoppingCart;
import com.yoga.studio.model.Section;

@Repository
public class ShoppingCartDAOImpl implements ShoppingCartDAO{

@Autowired
SessionFactory sessionFactory;

@Override
public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) 
{
	return (ShoppingCart)sessionFactory.getCurrentSession().merge(shoppingCart);
}

@Override
public List<ShoppingCart> listShoppingCarts() {
	// TODO Auto-generated method stub
	return sessionFactory.getCurrentSession().createCriteria(ShoppingCart.class).list();
}

//tuandang
@Override
public List<ShoppingCart> searchShoppingCarts(String searchText)
{
	searchText = searchText.trim().toLowerCase();
	String queryString = String.format("from ShoppingCart where name like '%%%s%%' or unit like '%%%s%%' ", searchText, searchText); 
	return sessionFactory.getCurrentSession().createQuery(queryString).list();
}

@Override
public ShoppingCart getShoppingCart(Long id) {
	// TODO Auto-generated method stub
	return (ShoppingCart) sessionFactory.getCurrentSession().get(ShoppingCart.class,id);
	}

@Override
public void deleteShoppingCart(Long id) {
	// TODO Auto-generated method stub
	ShoppingCart ShoppingCart = getShoppingCart(id);
	if(ShoppingCart!= null)
		sessionFactory.getCurrentSession().delete(ShoppingCart);
	
}

@Override
public boolean checkLogin(String userName, String userPassword) {
	  System.out.println("In Check login");
      Session session = sessionFactory.openSession();
      boolean userFound = false;
      //Query using Hibernate Query Language
      String SQL_QUERY =" from ShoppingCart as c where c.username=? and c.password=?";
      Query query = session.createQuery(SQL_QUERY);
      query.setParameter(0,userName);
      query.setParameter(1,userPassword);
      List list = query.list();

      if ((list != null) && (list.size() > 0)) {
              userFound= true;
      }

      session.close();
      return userFound;
}


}
