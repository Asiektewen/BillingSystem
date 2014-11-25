package com.telecom.billing.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_customer_info")
public class Customer extends User {
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "salesrep_id")
	private User user;
	@Column(name = "commission_level")
	private Integer commissionLevel;

	public Customer() {
		// TODO Auto-generated method stub
	}

}
