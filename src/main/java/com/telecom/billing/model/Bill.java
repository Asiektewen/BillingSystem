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
import javax.persistence.Transient;

/**
 * @author Eric
 * 
 */
@Entity
@Table(name = "t_month_call_detail")
public class Bill {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@Column(name = "src_phone_num")
	private String srcPhoneNo;
	
	@Column(name = "des_phone_num")
	private String destPhoneNo;
	
	@Column(name = "des_country_name")
	private String destCtyName;
	
	@Column(name = "duration")
	private double duration;

	@Column(name = "call_time")
	private String callTime;
	
	@Column(name = "cost")
	private double costOfCall;
	
	@Transient
	private double amtDue;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getDestPhoneNo() {
		return destPhoneNo;
	}

	public void setDestPhoneNo(String destPhoneNo) {
		this.destPhoneNo = destPhoneNo;
	}

	public String getSrcPhoneNo() {
		return srcPhoneNo;
	}

	public void setSrcPhoneNo(String srcPhoneNo) {
		this.srcPhoneNo = srcPhoneNo;
	}

	public String getDestCtyName() {
		return destCtyName;
	}

	public void setDestCtyName(String destCtyName) {
		this.destCtyName = destCtyName;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public String getCallTime() {
		return callTime;
	}

	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}

	public double getCostOfCall() {
		return costOfCall;
	}

	public void setCostOfCall(double costOfCall) {
		this.costOfCall = costOfCall;
	}

	public double getAmtDue() {
		return amtDue;
	}

	public void setAmtDue(double amtDue) {
		this.amtDue = amtDue;
	}

}
