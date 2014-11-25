package com.telecom.billing.dao;

import java.util.List;

import com.telecom.billing.model.Product;
import com.telecom.billing.model.Section;

public interface ProductDAO {
	
	/*
	 * CREATE and UPDATE
	 */
	public void saveProduct(Product product); // create and update

	/*
	 * READ
	 */
	public List<Product> listProducts();
	
	public Product getProduct(Long id);

	/*
	 * DELETE
	 */
	public void deleteProduct(Long id);
	
    public boolean checkLogin(String userName, String userPassword);
    
    //tuandang
    public List<Product> searchProducts(String searchText);
}
