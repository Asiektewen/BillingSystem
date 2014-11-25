package com.yoga.studio.services;

import java.util.List;

import com.yoga.studio.model.ShoppingCart;
import com.yoga.studio.model.Section;


public interface ShoppingCartService {
	/*
	 * CREATE and UPDATE 
	 */
	public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart);

	/*
	 * READ
	 */
	public List<ShoppingCart> listShoppingCarts();
	
	public ShoppingCart getShoppingCart(Long id);

	/*
	 * DELETE
	 */
	public void deleteShoppingCart(Long id);

	//tuandang
	public List<ShoppingCart> searchShoppingCarts(String searchText);

}
