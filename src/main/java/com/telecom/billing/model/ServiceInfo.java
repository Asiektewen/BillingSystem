/**
 * 
 */
package com.telecom.billing.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author zhangle
 *
 */
@Entity
@Table(name = "t_service_info")
public class ServiceInfo {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Column(name = "service_type")
	public String serviceType;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "country_code")
	public CountryInfo countryInfo;
	@Column(name = "peak_time")
	@DateTimeFormat(pattern = "HHmm")
	public String peakStartTime;
	@Column(name = "off_peak_time")
	// @Type(type = "date")
	// @DateTimeFormat(pattern = "HHmm")
	public String offpeakStartTime;
	@Column(name = "status")
	public String status;
	@Column(name = "update_history_id")
	public String updateHistoryID;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdateHistoryID() {
		return updateHistoryID;
	}

	public void setUpdateHistoryID(String updateHistoryID) {
		this.updateHistoryID = updateHistoryID;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the servviceType
	 */
	public String getServiceType() {
		return serviceType;
	}

	/**
	 * @param servviceType
	 *            the servviceType to set
	 */
	public void setServviceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public CountryInfo getCountryInfo() {
		return countryInfo;
	}

	public void setCountryInfo(CountryInfo countryInfo) {
		this.countryInfo = countryInfo;
	}

	public String getPeakStartTime() {
		return peakStartTime;
	}

	public void setPeakStartTime(String peakStartTime) {
		this.peakStartTime = peakStartTime;
	}

	public String getOffpeakStartTime() {
		return offpeakStartTime;
	}

	public void setOffpeakStartTime(String offpeakStartTime) {
		this.offpeakStartTime = offpeakStartTime;
	}

}
