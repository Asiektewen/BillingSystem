package com.telecom.billing.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.telecom.billing.dao.CommissionDAO;
import com.telecom.billing.model.Commission;

@Repository("commissionDAO")
public class CommissionDAOImpl extends GenericDAOImpl<Commission> implements
		CommissionDAO {

	@Override
	public void processCommissionBatch(String startTime,String endTime) {
		Query query = this
				.getCurrentSession()
				.createSQLQuery(
						"{call  get_service_summary(:start_date,:end_date)}")
				.setParameter("start_date", startTime)
				.setParameter("end_date", endTime);
		List result = query.list();

		
	}

	@Override
	public List<Commission> getMonthlyCommission() {
		Query query = getCurrentSession().createQuery("from Commission ");
		return query.list();
	}

}
