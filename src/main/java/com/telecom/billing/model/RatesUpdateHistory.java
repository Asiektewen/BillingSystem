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
@Table(name = "t_rate_update_history")
public class RatesUpdateHistory {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Column(name = "user_id")
	public Long userID;
	@Column(name = "file_name")
	public String fileName;
	@Column(name = "update_time")
	@Type(type = "date")
	@DateTimeFormat(pattern = "dd-mm-yyyy HH:mm")
	public Date updateTime;
	@Column(name = "effect_date")
	@Type(type = "date")
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	public Date effectDate;

	public Date getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
