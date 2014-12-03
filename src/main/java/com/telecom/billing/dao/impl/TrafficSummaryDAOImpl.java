package com.telecom.billing.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.telecom.billing.dao.TrafficSummaryDAO;
import com.telecom.billing.model.TrafficSummary;

@Repository("trafficSummaryDAO")
public class TrafficSummaryDAOImpl extends GenericDAOImpl<TrafficSummary>
		implements TrafficSummaryDAO {
	@Override
	public List<TrafficSummary> getMonthlyTraffics(String month) {
		List arrList = new ArrayList();
		// generate the traffic summary;
		for (int i = 0; i < 50; i++) {
			TrafficSummary traffic = new TrafficSummary();
			traffic.setServiceName("serveice" + i);
			traffic.setFromCtyName("from cty" + i);
			traffic.setToCtyName("to CTY" + i);
			traffic.setTotalMinsOfCalls((double) i);
			arrList.add(traffic);
		}
		return arrList;

	}

}
