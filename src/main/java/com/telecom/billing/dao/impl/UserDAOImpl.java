package com.telecom.billing.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.telecom.billing.dao.UserDAO;
import com.telecom.billing.model.Section;
import com.telecom.billing.model.User;

@Repository
public class UserDAOImpl implements UserDAO{

@Autowired
SessionFactory sessionFactory;

@Override
public void saveUser(User user) {
	// TODO Auto-generated method stub
	
	sessionFactory.getCurrentSession().merge(user);
	
}

@Override
public List<User> listUsers() {
	// TODO Auto-generated method stub
	return sessionFactory.getCurrentSession().createCriteria(User.class).list();
}

@Override
public User getUser(Long id) {
	// TODO Auto-generated method stub
	return (User) sessionFactory.getCurrentSession().get(User.class,id);
	}

@Override
public void deleteUser(Long id) {
	// TODO Auto-generated method stub
	User user = getUser(id);
	if(user!= null)
		sessionFactory.getCurrentSession().delete(user);
	
}

@Override
public User checkLogin(String userName, String userPassword) {
	  System.out.println("In Check login");
      Session session = sessionFactory.openSession();
      boolean userFound = false;
      //Query using Hibernate Query Language
      String SQL_QUERY =" from User as c where c.username=? and c.password=?";
      Query query = session.createQuery(SQL_QUERY);
      query.setParameter(0,userName);
      query.setParameter(1,userPassword);
      List list = query.list();

      if ((list != null) && (list.size() > 0)) {
              return (User) list.get(0);
      }

      session.close();
      return null;
}


}
