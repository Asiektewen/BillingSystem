/**
 * 
 */
package com.telecom.billing.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.telecom.billing.dao.RateHistoryDAO;
import com.telecom.billing.model.RateHistory;

/**
 * @author Eric
 * 
 */
@Repository("rateHistoryDAO")
public class RateHistoryDAOImpl extends GenericDAOImpl<RateHistory> implements
		RateHistoryDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.telecom.billing.dao.RateHistoryDAO#importRates(java.util.List)
	 */
	@Override
	public void importRates(List<RateHistory> rateList) {
		for (RateHistory rate : rateList) {
			this.save(rate);
		}
		Query query = this.getCurrentSession().createSQLQuery(
				"CALL update_rate()");
				List result = query.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.telecom.billing.dao.RateHistoryDAO#fetchRates(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public List<RateHistory> fetchRates(String srcCty, String service) {
		// TODO Auto-generated method stub
		return null;
	}

}
