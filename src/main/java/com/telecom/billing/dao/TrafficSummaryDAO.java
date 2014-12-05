package com.telecom.billing.dao;

import java.util.List;

import com.telecom.billing.model.TrafficSummary;

public interface TrafficSummaryDAO extends GenericDAO<TrafficSummary> {
	public void processMonthlyTrafic(String startTime,String endTime);

	public List<TrafficSummary> getMonthlyTraffics();

}
