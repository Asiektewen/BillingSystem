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

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
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
	@Column(name = "country_code")
	private String countryCode;
	@Column(name = "service_type")
	private String serviceType;
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
