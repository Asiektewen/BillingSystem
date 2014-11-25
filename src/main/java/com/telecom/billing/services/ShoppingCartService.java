package com.telecom.billing.services;

import java.util.List;

import com.telecom.billing.model.Section;
import com.telecom.billing.model.ShoppingCart;


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
