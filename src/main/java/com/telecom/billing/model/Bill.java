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
 * @author Eric
 * 
 */
@Entity
@Table(name = "t_month_call_detail")
public class Bill {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;

	@Column(name = "full_name", nullable = true)
	private String fullName;

	@Column(name = "src_phone_num", nullable = true)
	private String srcPhoneNo;

	@Column(name = "dest_phone_num", nullable = true)
	private String destPhoneNo;

	@Column(name = "service_type", nullable = true)
	private String serviceType;

	@Column(name = "src_country_code", nullable = true)
	private int srcCtyCode;

	@Column(name = "src_country_name", nullable = true)
	private String srcCtyName;

	@Column(name = "dest_country_code", nullable = true)
	private int desCtyCode;

	@Column(name = "dest_country_name", nullable = true)
	private String destCtyName;

	@Column(name = "duration", nullable = true)
	private int duration;

	@Column(name = "call_date", nullable = true)
	@Type(type = "date")
	@DateTimeFormat(pattern = "mm/dd/yyyy hh:mm:ss")
	public Date callDate;

	@Column(name = "call_time", nullable = true)
	private int callTime;

	@Column(name = "off_peak_time", nullable = true)
	private int offPeakTime;

	@Column(name = "peak_time", nullable = true)
	private int peakTime;

	@Column(name = "peak", nullable = true)
	private float peak;

	@Column(name = "off_peak", nullable = true)
	private float offPeak;

	@Column(name = "cost", nullable = true)
	private float costOfCall;

	@Transient
	private double amtDue;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSrcPhoneNo() {
		return srcPhoneNo;
	}

	public void setSrcPhoneNo(String srcPhoneNo) {
		this.srcPhoneNo = srcPhoneNo;
	}

	public String getDestPhoneNo() {
		return destPhoneNo;
	}

	public void setDestPhoneNo(String destPhoneNo) {
		this.destPhoneNo = destPhoneNo;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public int getSrcCtyCode() {
		return srcCtyCode;
	}

	public void setSrcCtyCode(int srcCtyCode) {
		this.srcCtyCode = srcCtyCode;
	}

	public String getSrcCtyName() {
		return srcCtyName;
	}

	public void setSrcCtyName(String srcCtyName) {
		this.srcCtyName = srcCtyName;
	}

	public int getDesCtyCode() {
		return desCtyCode;
	}

	public void setDesCtyCode(int desCtyCode) {
		this.desCtyCode = desCtyCode;
	}

	public String getDestCtyName() {
		return destCtyName;
	}

	public void setDestCtyName(String destCtyName) {
		this.destCtyName = destCtyName;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getCallDate() {
		return callDate;
	}

	public void setCallDate(Date callDate) {
		this.callDate = callDate;
	}

	public int getCallTime() {
		return callTime;
	}

	public void setCallTime(int callTime) {
		this.callTime = callTime;
	}

	public int getOffPeakTime() {
		return offPeakTime;
	}

	public void setOffPeakTime(int offPeakTime) {
		this.offPeakTime = offPeakTime;
	}

	public int getPeakTime() {
		return peakTime;
	}

	public void setPeakTime(int peakTime) {
		this.peakTime = peakTime;
	}

	public float getPeak() {
		return peak;
	}

	public void setPeak(float peak) {
		this.peak = peak;
	}

	public float getOffPeak() {
		return offPeak;
	}

	public void setOffPeak(float offPeak) {
		this.offPeak = offPeak;
	}

	public float getCostOfCall() {
		return costOfCall;
	}

	public void setCostOfCall(float costOfCall) {
		this.costOfCall = costOfCall;
	}

	public double getAmtDue() {
		return amtDue;
	}

	public void setAmtDue(double amtDue) {
		this.amtDue = amtDue;
	}

}
