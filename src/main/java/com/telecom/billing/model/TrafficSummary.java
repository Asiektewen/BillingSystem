/**
 * 
 */
package com.telecom.billing.model;

/**
 * @author Eric
 *
 */
public class TrafficSummary {
	private String serviceName;
	private String fromCtyName;
	private String toCtyName;
	private Double totalMinsOfCalls;
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getFromCtyName() {
		return fromCtyName;
	}
	public void setFromCtyName(String fromCtyName) {
		this.fromCtyName = fromCtyName;
	}
	public String getToCtyName() {
		return toCtyName;
	}
	public void setToCtyName(String toCtyName) {
		this.toCtyName = toCtyName;
	}
	public Double getTotalMinsOfCalls() {
		return totalMinsOfCalls;
	}
	public void setTotalMinsOfCalls(Double totalMinsOfCalls) {
		this.totalMinsOfCalls = totalMinsOfCalls;
	}

	
}
