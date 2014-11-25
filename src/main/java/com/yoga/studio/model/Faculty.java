package com.yoga.studio.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="USER_ID")
public class Faculty extends User {
	
	 public Faculty() {
		// TODO Auto-generated constructor stub
	}
	 public Faculty(User user) {
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
	 
	@OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
	 private List<Section> sections;
	 
	@OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
	 private List<Customer> customers;
	 
	 private String description;

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	 
	 

}
