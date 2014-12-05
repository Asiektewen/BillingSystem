/**
 * 
 */
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

/**
 * @author zhangle
 *
 */
@Entity
@Table(name = "t_rate_history_temp")
public class RateHistoryTemp {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Column(name = "service_type")
	public String servviceType;
	
	@Column(name = "src_country_name")
	public String srcCountry;
	
	@Column(name = "dest_country_code")
	public Integer destCountryId;
	
	@Column(name = "effective_time")
	@Type(type = "date")
	@DateTimeFormat(pattern = "mm/dd/yyyy hh:mm:ss")
	public Date startTime;

	@Column(name = "peak")
	public Double peakRate;
	
	@Column(name = "off_peak")
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
	public Integer getDestCountryId() {
		return destCountryId;
	}

	/**
	 * @param destCountry
	 *            the destCountry to set
	 */
	public void setDestCountryId(Integer destCountryId) {
		this.destCountryId = destCountryId;
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


}
