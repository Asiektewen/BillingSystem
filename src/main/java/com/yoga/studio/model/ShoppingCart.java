package com.yoga.studio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ShoppingCart {		
	 @Id
	 @GeneratedValue
	 private Long id;
	 
	 private double totalAmount;
	 private String shippingInfo;
	 private String paymentInfo;
	 
	 public String getShippingInfo() {
		return shippingInfo;
	}

	public void setShippingInfo(String shippingInfo) {
		this.shippingInfo = shippingInfo;
	}

	public String getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(String paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
	@OneToMany (cascade = CascadeType.ALL)
	 private List<ShoppingCartItem> shoppingItems;

	 @OneToOne(mappedBy = "shoppingCart")
	    private Customer customer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<ShoppingCartItem> getShoppingItems() {
		return shoppingItems;
	}

	public void setShoppingItems(List<ShoppingCartItem> shoppingItems) {
		this.shoppingItems = shoppingItems;
	}
	public void payShoppingCart() 
	{
		this.shoppingItems.clear();
		updateTotalAmount();
	}
	public void addShoppingCartItem(Product product) 
	{
		if(shoppingItems == null)
			shoppingItems = new ArrayList<ShoppingCartItem>();
		
		//check if product exists
		ShoppingCartItem existItem = null;
		for (ShoppingCartItem item : shoppingItems) 
		{
			if(item.getProduct().getId() == product.getId())
			{
				existItem = item;
				break;
			}
		}
		
		if(existItem != null)
		{
			existItem.setQuantity(existItem.getQuantity() + 1);
		}
		else
		{
			ShoppingCartItem newItem = new ShoppingCartItem();
			newItem.setProduct(product);	
			newItem.setQuantity(1.0);
			shoppingItems.add(newItem);
		}
		updateTotalAmount();
	}
	public void deleteShoppingCartItem(Long shoppingCartItemId) 
	{
		ShoppingCartItem removeItem = null;
		for (ShoppingCartItem item : shoppingItems) 
		{
			if(item.getId() == shoppingCartItemId)
			{
				removeItem = item;
				break;
			}
		}
		if(removeItem  != null)
		{
			shoppingItems.remove(removeItem);		
			updateTotalAmount();
			System.out.printf("totalAmount = %f", totalAmount);
		}
	}
	private void updateTotalAmount()
	{
		//calculate
		totalAmount = 0;
		if(shoppingItems == null)
			return;
		
		for (ShoppingCartItem item : shoppingItems) 
		{
			totalAmount += item.getAmount();
		}
	}
}
