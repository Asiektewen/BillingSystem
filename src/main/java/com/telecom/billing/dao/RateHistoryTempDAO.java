/**
 * 
 */
package com.telecom.billing.dao;

import java.util.List;

import com.telecom.billing.model.RateHistoryTemp;

/**
 * @author Eric
 * 
 */
public interface RateHistoryTempDAO extends GenericDAO<RateHistoryTemp>{

	public void importRates(List<RateHistoryTemp> rateList);
	public void processRateUpdate();

}
