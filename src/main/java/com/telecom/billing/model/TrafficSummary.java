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
 * @author Eric
 *
 */
@Entity
@Table(name = "t_month_service_summary")
public class TrafficSummary {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	@Column(name="service_type")
	private String serviceName;
	
	@Column(name="src_country_code")
	private int srcCtyCode;	
	
	@Column(name="src_country_name")
	private String fromCtyName;
	
	@Column(name="dest_country_code")
	private int desCtyCode;	
	
	@Column(name = "dest_country_name")
	private String toCtyName;
	
	@Column(name = "sum_duration")
	private int totalMinsOfCalls;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public int getSrcCtyCode() {
		return srcCtyCode;
	}

	public void setSrcCtyCode(int srcCtyCode) {
		this.srcCtyCode = srcCtyCode;
	}

	public String getFromCtyName() {
		return fromCtyName;
	}

	public void setFromCtyName(String fromCtyName) {
		this.fromCtyName = fromCtyName;
	}

	public int getDesCtyCode() {
		return desCtyCode;
	}

	public void setDesCtyCode(int desCtyCode) {
		this.desCtyCode = desCtyCode;
	}

	public String getToCtyName() {
		return toCtyName;
	}

	public void setToCtyName(String toCtyName) {
		this.toCtyName = toCtyName;
	}

	public int getTotalMinsOfCalls() {
		return totalMinsOfCalls;
	}

	public void setTotalMinsOfCalls(int totalMinsOfCalls) {
		this.totalMinsOfCalls = totalMinsOfCalls;
	}
	
	
}
