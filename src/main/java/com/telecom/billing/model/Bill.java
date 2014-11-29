/**
 * 
 */
package com.telecom.billing.model;

/**
 * @author Eric
 * 
 */
public class Bill {
	private String srcPhoneNo;
	private String destPhoneNo;
	private String destCtyName;
	private double duration;

	private String callTime;
	private double costOfCall;
	private double amtDue;

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
