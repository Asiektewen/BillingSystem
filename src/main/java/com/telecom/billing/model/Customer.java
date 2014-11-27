package com.telecom.billing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_customer_info")
public class Customer extends User {
	@Column(name = "salesrep_id")
	private Long salesRepID;

	public Long getSalesRepID() {
		return salesRepID;
	}

	public void setSalesRepID(Long salesRepID) {
		this.salesRepID = salesRepID;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountryID() {
		return countryID;
	}

	public void setCountryID(String countryID) {
		this.countryID = countryID;
	}

	public String getServiceID() {
		return serviceID;
	}

	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	@Column(name = "commission_level")
	private String commissionlevel;

	public String getCommissionlevel() {
		return commissionlevel;
	}

	public void setCommissionlevel(String commissionlevel) {
		this.commissionlevel = commissionlevel;
	}

	@Column(name = "city")
	private String city;
	@Column(name = "countryID")
	private String countryID;
	@Column(name = "serviceID")
	private String serviceID;
	@Column(name = "street")
	private String street;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Customer() {
		// TODO Auto-generated method stub
	}

}
