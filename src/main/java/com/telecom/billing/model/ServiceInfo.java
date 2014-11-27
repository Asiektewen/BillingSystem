/**
 * 
 */
package com.telecom.billing.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
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
	@GeneratedValue
	public Long id;

	@Column(name = "service_type")
	public String servviceType;
	@Column(name = "country_num")
	public String countryNum;
	@Column(name = "peak_start_time")
	@Type(type = "date")
	@DateTimeFormat(pattern = "HHmm")
	public Date peakStartTime;
	@Column(name = "offpeak_start_time")
	@Type(type = "date")
	@DateTimeFormat(pattern = "HHmm")
	public Date offpeakStartTime;
	@Column(name = "status")
	public int status;
	@Column(name = "update_history_id")
	public String updateHistoryID;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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
	public String getServviceType() {
		return servviceType;
	}

	/**
	 * @param servviceType
	 *            the servviceType to set
	 */
	public void setServviceType(String servviceType) {
		this.servviceType = servviceType;
	}

	public String getCountryNum() {
		return countryNum;
	}

	public void setCountryNum(String countryNum) {
		this.countryNum = countryNum;
	}

	public Date getPeakStartTime() {
		return peakStartTime;
	}

	public void setPeakStartTime(Date peakStartTime) {
		this.peakStartTime = peakStartTime;
	}

	public Date getOffpeakStartTime() {
		return offpeakStartTime;
	}

	public void setOffpeakStartTime(Date offpeakStartTime) {
		this.offpeakStartTime = offpeakStartTime;
	}

}
