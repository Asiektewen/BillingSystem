/**
 * 
 */
package com.telecom.billing.dao;

import java.util.List;

import com.telecom.billing.model.RateHistory;

/**
 * @author Eric
 * 
 */
public interface RateHistoryDAO {

	public void importRates(List<RateHistory> rateList);

	public List<RateHistory> fetchRates(String srcCty, String service);

}
