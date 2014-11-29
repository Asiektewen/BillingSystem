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

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author zhangle
 *
 */
@Entity
@Table(name = "t_call_detail")
public class CallDetail {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	@Column(name = "service_type")
	public String servviceType;
	@Column(name = "src_country_id")
	public Integer srcCountryId;
	@Column(name = "dest_country_id")
	public Integer destCountryId;
	@Column(name = "src_phone_num")
	public String srcPhoneNumber;
	@Column(name = "dest_phone_num")
	public String destPhoneNumber;
	@Column(name = "call_date")
	@Type(type = "date")
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	public Date callDate;
	@Column(name = "call_time")
	public String callTime;
	@Column(name = "duration")
	public Integer duration;

	public String getServviceType() {
		return servviceType;
	}

	public void setServviceType(String servviceType) {
		this.servviceType = servviceType;
	}

	public Integer getSrcCountryId() {
		return srcCountryId;
	}

	public void setSrcCountryId(Integer srcCountryId) {
		this.srcCountryId = srcCountryId;
	}

	public Integer getDestCountryId() {
		return destCountryId;
	}

	public void setDestCountryId(Integer destCountryId) {
		this.destCountryId = destCountryId;
	}

	public String getSrcPhoneNumber() {
		return srcPhoneNumber;
	}

	public void setSrcPhoneNumber(String srcPhoneNumber) {
		this.srcPhoneNumber = srcPhoneNumber;
	}

	public String getDestPhoneNumber() {
		return destPhoneNumber;
	}

	public void setDestPhoneNumber(String destPhoneNumber) {
		this.destPhoneNumber = destPhoneNumber;
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
	 * @return the callDate
	 */
	public Date getCallDate() {
		return callDate;
	}

	/**
	 * @param callDate
	 *            the callDate to set
	 */
	public void setCallDate(Date callDate) {
		this.callDate = callDate;
	}

	/**
	 * @return the callTime
	 */
	public String getCallTime() {
		return callTime;
	}

	/**
	 * @param callTime
	 *            the callTime to set
	 */
	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

}
