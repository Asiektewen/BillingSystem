/**
 /* Author: Hiwot
 */
package com.telecom.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.telecom.billing.model.Product;
import com.telecom.billing.services.ProductService;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({ "user" })
public class ProductController {

	@Autowired
	ProductService productService;

	@RequestMapping(value = "/searchProducts", method = RequestMethod.POST)
	public String searchProducts(Model model,
			@RequestParam("searchText") String searchText) {
		System.out.printf("searchText =%s", searchText);
		// search searchText in name, unit
		model.addAttribute("listProducts",
				productService.searchProducts(searchText));
		return "browseProducts";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/listProducts", method = RequestMethod.GET)
	public String requestToListOfProducts(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("listProducts", this.productService.listProducts());
		return "listProducts";
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") Product product,
			BindingResult result) {
		productService.saveProduct(product);
		return "redirect:/listProducts";

	}

	@RequestMapping(value = "/editProduct", method = RequestMethod.GET)
	public String editproduct(@RequestParam("productId") String productId,
			@RequestParam("name") String name,
			@RequestParam("picurl") String picurl,
			@RequestParam("price") double price,
			@RequestParam("unit") String unit) {
		Product product = productService.getProduct(Long.parseLong(productId));
		if (product != null) {
			product.setName(name);

			product.setPicurl(picurl);
			product.setPrice(price);
			product.setUnit(unit);

			productService.saveProduct(product);
		}
		return "redirect:/listProducts";
	}

	@RequestMapping(value = { "editProduct/products/add" }, method = RequestMethod.POST)
	public String addProduct1(@ModelAttribute("product") Product product,
			BindingResult result) {
		productService.saveProduct(product);

		return "redirect:/listProducts";
	}

	// @RequestMapping(value = "/listProducts", method = RequestMethod.GET)
	// public String listProducts(Locale locale, Map<String, Object> map) {
	// List<Product> products = productService.listProducts();
	// map.put("products", products);
	//
	// return "listProducts";
	// }
	@RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
	// public String deleteProduct(@PathVariable Long productId, Locale locale,
	// Map<String, Object> map)
	public String deleteProduct(@RequestParam("productId") String productId,
			@RequestParam("name") String title) {
		// System.out.printf("productId = %d ", productId);
		productService.deleteProduct(Long.parseLong(productId));
		return "redirect:/listProducts";
	}

}
