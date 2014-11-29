/**
 * 
 */
package com.telecom.billing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zhangle
 *
 */
@Entity
@Table(name = "t_country_code")
public class CountryInfo {

	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	@Id
	@Column(name = "country_code")
	public String countryCode;
	@Column(name = "country_name")
	public String countryName;

	// @OneToMany(mappedBy = "countryInfo", cascade =
	// CascadeType.ALL,fetch=FetchType.LAZY)
	// private List<ServiceInfo> serviceInfoList;

	// public List<ServiceInfo> getServiceInfoList() {
	// return serviceInfoList;
	// }
	//
	// public void setServiceInfoList(List<ServiceInfo> serviceInfoList) {
	// this.serviceInfoList = serviceInfoList;
	// }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}
