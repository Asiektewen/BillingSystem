package com.yoga.studio.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.JoinColumn;





import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.IndexColumn;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@PrimaryKeyJoinColumn(name="USER_ID")

public class Customer extends User {
	public  Customer() {
		// TODO Auto-generated method stub
		this.enrollments = new ArrayList<Enrollment>();
	}
	public Customer(User user) {
		// TODO Auto-generated constructor stub
		this.setId(user.getId());
		this.setFullName(user.getFullName());
		this.setDob(user.getDob());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setRole(user.getRole());
		this.setContactInformation(user.getContactInformation());
		this.setGender(user.getGender());
		this.setEmail(user.getEmail());
		
	}
	 @ManyToOne(cascade=CascadeType.ALL)
	 @JoinColumn(name = "faculty_id")  
	 private Faculty faculty;
	
	 @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
     private List<Enrollment> enrollments;
	 
	 @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	 private List<Waiver> waivers;
	 
	 @OneToOne
	 @JoinColumn(name = "shoppingCart_id")
	 private ShoppingCart shoppingCart;
	
	 @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	 private List<Order> orders;
	 
	public List<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	public List<Waiver> getWaivers() {
		return waivers;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public ShoppingCart getShoppingCart() {

		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public void setWaivers(List<Waiver> waivers) {
		this.waivers = waivers;
	}
	 
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		boolean result=false;
		Customer customer;
		if(obj instanceof Customer){
			 customer = (Customer) obj;
			 result = (this.getId()==customer.getId() && this.getUsername().equals(customer.getUsername()));
		}
		return result;
	}
	 


}
