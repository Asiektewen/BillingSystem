package com.telecom.billing.model;

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
@PrimaryKeyJoinColumn(name = "USER_ID")
public class Customer extends User {
	public Customer() {
		// TODO Auto-generated method stub
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

}
