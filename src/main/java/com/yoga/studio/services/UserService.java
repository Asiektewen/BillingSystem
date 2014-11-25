package com.yoga.studio.services;

import java.util.List;

import com.yoga.studio.model.User;


public interface UserService {
	/*
	 * CREATE and UPDATE 
	 */
	public void saveUser(User user);

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
