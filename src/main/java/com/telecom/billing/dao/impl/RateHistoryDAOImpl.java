/**
 * 
 */
package com.telecom.billing.dao.impl;

import java.util.Date;
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
	 * @see com.telecom.billing.dao.RateHistoryDAO#fetchRates(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public List<RateHistory> fetchRates(String srcCty, String service,Date date) {
		String SQL_QUERY = " from RateHistory c where c.srcCountry=? and c.servviceType=? and c.startTime <=? and endTime>?";
		Query query = getCurrentSession().createQuery(SQL_QUERY);
		query.setParameter(0, srcCty);
		query.setParameter(1, service);
		query.setParameter(2, date);
		query.setParameter(3, date);
		
		List<RateHistory> list = query.list();
		return list;
	}

}
