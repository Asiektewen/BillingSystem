/**
 * 
 */
package com.telecom.billing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zhangle
 *
 */
@Entity
@Table(name = "t_country_info")
public class CountryInfo {
	@Id
	@Column(name = "id")
	@GeneratedValue
	public Long id;

	@Column(name = "country_num")
	public String countryNum;
	@Column(name = "country_name")
	public String countryName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryNum() {
		return countryNum;
	}

	public void setCountryNum(String countryNum) {
		this.countryNum = countryNum;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}
