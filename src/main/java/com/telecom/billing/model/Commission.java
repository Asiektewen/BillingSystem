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
 * @author QZhang
 * 
 */
@Entity
@Table(name = "t_month_commission_detail")
public class Commission {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	
	@Column(name=" customer_full_name")
	private String fullName;
	
	@Column(name="salesrep_id")
	private int fullsalsRepId;
	
	@Column(name="salesrep_name")
	private String salsRep;
	
	@Column(name="commission_level")
	private float commissioinLevel;
	
	
	@Column(name="commission")
	private float totalCommission;

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

	public int getFullsalsRepId() {
		return fullsalsRepId;
	}

	public void setFullsalsRepId(int fullsalsRepId) {
		this.fullsalsRepId = fullsalsRepId;
	}

	public String getSalsRep() {
		return salsRep;
	}

	public void setSalsRep(String salsRep) {
		this.salsRep = salsRep;
	}

	public float getCommissioinLevel() {
		return commissioinLevel;
	}

	public void setCommissioinLevel(float commissioinLevel) {
		this.commissioinLevel = commissioinLevel;
	}


	public float getTotalCommission() {
		return totalCommission;
	}

	public void setTotalCommission(float totalCommission) {
		this.totalCommission = totalCommission;
	}
	
	


}
