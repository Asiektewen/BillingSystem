package com.yoga.studio.dao;

import java.util.List;

import com.yoga.studio.model.ShoppingCart;
import com.yoga.studio.model.Section;

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
