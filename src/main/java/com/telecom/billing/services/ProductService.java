package com.telecom.billing.services;

import java.util.List;

import com.telecom.billing.model.Product;
import com.telecom.billing.model.Section;


public interface ProductService {
	/*
	 * CREATE and UPDATE 
	 */
	public void saveProduct(Product product);

	/*
	 * READ
	 */
	public List<Product> listProducts();
	
	public Product getProduct(Long id);

	/*
	 * DELETE
	 */
	public void deleteProduct(Long id);

	//tuandang
	public List<Product> searchProducts(String searchText);

}
