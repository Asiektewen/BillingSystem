/**
 * 
 */
package com.telecom.billing.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.telecom.billing.dao.RateHistoryTempDAO;
import com.telecom.billing.model.RateHistoryTemp;

/**
 * @author Eric
 * 
 */
@Repository("rateHistoryTempDAO")
public class RateHistoryTempDAOImpl extends GenericDAOImpl<RateHistoryTemp> implements
RateHistoryTempDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.telecom.billing.dao.RateHistoryDAO#importRates(java.util.List)
	 */
	@Override
	public void importRates(List<RateHistoryTemp> rateList) {
		for (RateHistoryTemp rate : rateList) {
			System.out.println(rate.getSrcCountry());;
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
	public List<RateHistoryTemp> fetchRates(String srcCty, String service) {
		// TODO Auto-generated method stub
		return null;
	}

}
