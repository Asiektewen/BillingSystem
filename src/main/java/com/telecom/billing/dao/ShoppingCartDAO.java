package com.telecom.billing.dao;

import java.util.List;

import com.telecom.billing.model.Section;
import com.telecom.billing.model.ShoppingCart;

public interface ShoppingCartDAO {
	
	/*
	 * CREATE and UPDATE
	 */
	public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart); // create and update

	/*
	 * READ
	 */
	public List<ShoppingCart> listShoppingCarts();
	
	public ShoppingCart getShoppingCart(Long id);

	/*
	 * DELETE
	 */
	public void deleteShoppingCart(Long id);
	
    public boolean checkLogin(String userName, String userPassword);
    
    //tuandang
    public List<ShoppingCart> searchShoppingCarts(String searchText);
}
