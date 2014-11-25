package com.yoga.studio.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yoga.studio.model.Course;
import com.yoga.studio.model.Customer;
import com.yoga.studio.model.Product;
import com.yoga.studio.model.ShoppingCart;
import com.yoga.studio.model.ShoppingCartItem;
import com.yoga.studio.model.User;
import com.yoga.studio.services.CustomerService;
import com.yoga.studio.services.ProductService;
import com.yoga.studio.services.ShoppingCartService;


/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({"user", "listProducts"})

public class ShoppingCartController {

	@Autowired
	ShoppingCartService shoppingCartService;
	@Autowired
	CustomerService customerService;
	@Autowired
	ProductService productService ;
	

	@RequestMapping(value = "/browseProducts", method = RequestMethod.GET)
	public String browseProducts(@ModelAttribute("user") User user, Model model) 
	{
		System.out.printf("user %s  %s", user.getUsername(), user.getRole());
		//search searchText in name, unit
		model.addAttribute("listProducts", productService.searchProducts(""));
	
		return "browseProducts";	
	}
	@RequestMapping(value = "/confirmShoppingCart", method = RequestMethod.POST)
	public String confirmShoppingCart(
			@RequestParam("shippingInfo") String shippingInfo, 
			@RequestParam("paymentInfo") String paymentInfo, 
			@ModelAttribute("user") User user, 			
			Model model) 
	{
		
		model.addAttribute("user", user);
		if(user instanceof Customer)
		{
			ShoppingCart cart = ((Customer) user).getShoppingCart();
			cart.setShippingInfo(shippingInfo);
			cart.setPaymentInfo(paymentInfo);
			
			//remove the items from shopping cart
			cart.payShoppingCart();
			shoppingCartService.saveShoppingCart(cart);
		}
		
		
		return "orderNotification";
	}
	@RequestMapping(value = "/browseShoppingCart", method = RequestMethod.GET)
	public String browseShoppingCart(@ModelAttribute("user") User user, Model model) 
	{
		model.addAttribute("user", user);

		return "browseShoppingCart";
	}

	@RequestMapping(value = {"/deleteShoppingCartItem/{itemId}"}, method = RequestMethod.GET)
	public String deleteShoppingCartItem(@PathVariable Long itemId, @ModelAttribute("user") User user, Model model) 
	{
		if(user instanceof Customer)
		{
			ShoppingCart cart = ((Customer) user).getShoppingCart();
			cart.deleteShoppingCartItem(itemId);

			shoppingCartService.saveShoppingCart(cart);
		}
		model.addAttribute("user", user);

		return "browseShoppingCart";
	}	

	@RequestMapping(value = {"/viewDetailProduct/{productId}"}, method = RequestMethod.GET)
	public String viewDetailProduct( @ModelAttribute("user") User user, @PathVariable Long productId, Locale locale, Map<String, Object> map) 
	{
		Product product = productService.getProduct(productId);
		map.put("product", product);
		return "viewDetailProduct";
	}	
	@RequestMapping(value = {"/addToShoppingCart/{productId}"}, method = RequestMethod.GET)
	public String addToShoppingCart(@PathVariable Long productId, @ModelAttribute("user") User user, Locale locale, Map<String, Object> map) {
		Product product = productService.getProduct(productId);
		
		if(user instanceof Customer)
		{
			ShoppingCart cart = ((Customer) user).getShoppingCart();	
			if(cart==null)	
			{
				cart = new ShoppingCart();
				cart = shoppingCartService.saveShoppingCart(cart);
				((Customer) user).setShoppingCart(cart);
			}
			cart.addShoppingCartItem(product);
			customerService.saveCustomer((Customer) user);
			shoppingCartService.saveShoppingCart(cart);
		}

		return "redirect:/browseProducts";
	}
	@RequestMapping(value = "/deleteShoppingCart/{ShoppingCartId}", method = RequestMethod.GET)
	public String deleteShoppingCart(@PathVariable Long shoppingCartId, Locale locale) {
		shoppingCartService.deleteShoppingCart(shoppingCartId);
		return "redirect:/listShoppingCarts";
	}
}
