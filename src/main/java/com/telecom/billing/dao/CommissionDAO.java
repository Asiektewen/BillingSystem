package com.telecom.billing.dao;

import java.util.List;

import com.telecom.billing.model.Commission;

public interface CommissionDAO extends GenericDAO<Commission> {
	public void processCommissionBatch(String startTime,String endTime);

	public List<Commission> getMonthlyCommission();

}
