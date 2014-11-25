package com.yoga.studio.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Enrollment implements Comparable<Enrollment> {
	
	 public Enrollment() {
		// TODO Auto-generated constructor stub
	 }
	 
	 public Enrollment(Customer customer, Section section) {
			// TODO Auto-generated constructor stub
		 this.customer=customer;
		 this.section=section;
		}
		 
	 @Id
	 @GeneratedValue
	 private long id;
	 
	 @ManyToOne(cascade=CascadeType.ALL)
	 @JoinColumn(name = "customer_id")  
	 private Customer customer;
	 
	 @ManyToOne
	 @JoinColumn(name="section_id")
	 private Section section;
	 
	 public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	private String enrollmentStatus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getEnrollmentStatus() {
		return enrollmentStatus;
	}

	public void setEnrollmentStatus(String enrollmentStatus) {
		this.enrollmentStatus = enrollmentStatus;
	}

	@Override
	public int compareTo(Enrollment e) {
		// TODO Auto-generated method stub
		return (int) (this.id-e.id);
	}
	 
	 

}
