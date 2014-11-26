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
@Table(name = "t_rate_history")
public class RateHistory {
	@Id
	@Column(name = "id")
	@GeneratedValue
	public Long id;

	@Column(name = "service_type")
	public String servviceType;
	@Column(name = "src_country")
	public String srcCountry;
	@Column(name = "dest_country")
	public String destCountry;
	@Column(name = "start_time")
	public Date startTime;
	@Column(name = "end_time")
	@Type(type = "date")
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	public Date endTime;
	@Column(name = "peak_rate")
	public Double peakRate;
	@Column(name = "offpeak_rate")
	public Double offpeakRate;

	/**
	 * @return the peakRate
	 */
	public Double getPeakRate() {
		return peakRate;
	}

	/**
	 * @param peakRate
	 *            the peakRate to set
	 */
	public void setPeakRate(Double peakRate) {
		this.peakRate = peakRate;
	}

	/**
	 * @return the offpeakRate
	 */
	public Double getOffpeakRate() {
		return offpeakRate;
	}

	/**
	 * @param offpeakRate
	 *            the offpeakRate to set
	 */
	public void setOffpeakRate(Double offpeakRate) {
		this.offpeakRate = offpeakRate;
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

	/**
	 * @return the srcCountry
	 */
	public String getSrcCountry() {
		return srcCountry;
	}

	/**
	 * @param srcCountry
	 *            the srcCountry to set
	 */
	public void setSrcCountry(String srcCountry) {
		this.srcCountry = srcCountry;
	}

	/**
	 * @return the destCountry
	 */
	public String getDestCountry() {
		return destCountry;
	}

	/**
	 * @param destCountry
	 *            the destCountry to set
	 */
	public void setDestCountry(String destCountry) {
		this.destCountry = destCountry;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
