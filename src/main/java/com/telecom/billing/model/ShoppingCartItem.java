package com.telecom.billing.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//@Entity
public class ShoppingCartItem {

	 @Id
	 @GeneratedValue
	 private int id;
	 
	 private double quantity;
	 
	 private double amount;
	 
	 @ManyToOne
	 @JoinColumn(name="product_id")
	 private Product product;
	 
	    @ManyToOne
	    @JoinColumn(name="shoppingCart_id")
	    private ShoppingCart shoppingCart;
	 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
		if(product != null)
			this.amount = quantity * product.getPrice();
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
		this.amount = quantity * product.getPrice();
System.out.printf("setProduct quantity = %f, price = %f", quantity , product.getPrice());		
	}
	 
	 
	 
}
