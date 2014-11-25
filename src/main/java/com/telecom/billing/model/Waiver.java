package com.telecom.billing.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

//@Entity
public class Waiver {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@OneToOne
	private Course course;

	private String waiverStatus;

	private String reason;

	private String feedback;

	public Waiver() {
		this.waiverStatus = "0";
	}

	public Waiver(Customer customer2, Course course2) {
		this.customer = customer2;
		this.course = course2;
		this.waiverStatus = "0";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		customer = customer;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getWaiverStatus() {
		return waiverStatus;
	}

	public void setWaiverStatus(String waiverStatus) {
		this.waiverStatus = waiverStatus;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

}
