package com.telecom.billing.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "t_customer_info")
public class Customer {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;

	@Column(name = "role")
	private String role;

	@Transient
	private boolean status;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "birth_date")
	@Type(type = "date")
	private Date dob;

	@Column(name = "join_date")
	@Type(type = "date")
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private Date joinDate;

	@Column(name = "gender")
	private int gender;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	private String email;

	@Column(name = "phone_num")
	private String phoneNumber;

	@Column(name = "address")
	private String address;

	@Column(name = "zipCode")
	private String zipCode;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getFullName() {

		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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
	@Column(name = "country_name")
	private String countryName;

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

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
