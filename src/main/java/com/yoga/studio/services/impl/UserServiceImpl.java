package com.yoga.studio.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoga.studio.dao.UserDAO;
import com.yoga.studio.model.User;
import com.yoga.studio.services.UserService;

import org.springframework.transaction.annotation.Propagation;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDAO userDAO;

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		user.setJoinDate(new Date());
		userDAO.saveUser(user);
		
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<User> listUsers() {
		// TODO Auto-generated method stub
		return userDAO.listUsers();
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public User getUser(Long id) {
		// TODO Auto-generated method stub
		return userDAO.getUser(id);	
		}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		userDAO.deleteUser(id);
		
	}

	@Override
	public User checkLogin(String userName, String userPassword) {
		// TODO Auto-generated method stub
		return userDAO.checkLogin(userName, userPassword);
	}

}
