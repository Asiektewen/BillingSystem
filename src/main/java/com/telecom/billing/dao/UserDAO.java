package com.telecom.billing.dao;

import java.util.List;

import com.telecom.billing.model.User;



public interface UserDAO {
	/*
	 * CREATE and UPDATE
	 */
	public void saveUser(User user); // create and update

	/*
	 * READ
	 */
	public List<User> listUsers();
	public User getUser(Long id);

	/*
	 * DELETE
	 */
	public void deleteUser(Long id);
	
    public User checkLogin(String userName, String userPassword);


}
