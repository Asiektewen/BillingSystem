package com.telecom.billing.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.telecom.billing.dao.TrafficSummaryDAO;
import com.telecom.billing.model.TrafficSummary;

@Repository("trafficSummaryDAO")
public class TrafficSummaryDAOImpl extends GenericDAOImpl<TrafficSummary>
		implements TrafficSummaryDAO {

	@Override
	public void processMonthlyTrafic(String startTime, String endTime) {
		Query query = this
				.getCurrentSession()
				.createSQLQuery(
						"{call  get_service_summary(:start_date,:end_date)}")
				.setParameter("start_date", startTime)
				.setParameter("end_date", endTime);
		List result = query.list();

	}

	@Override
	public List<TrafficSummary> getMonthlyTraffics() {
		Query query = getCurrentSession().createQuery("from TrafficSummary ");
		return query.list();
	}

}
