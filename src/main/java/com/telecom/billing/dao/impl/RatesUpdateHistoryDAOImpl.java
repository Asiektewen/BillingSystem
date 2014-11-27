/**
 * 
 */
package com.telecom.billing.dao.impl;

import org.springframework.stereotype.Repository;

import com.telecom.billing.dao.RatesUpdateHistoryDAO;
import com.telecom.billing.model.RatesUpdateHistory;

/**
 * @author zhangle
 *
 */
@Repository("ratesUpdateHistoryDAO")
public class RatesUpdateHistoryDAOImpl extends
		GenericDAOImpl<RatesUpdateHistory> implements RatesUpdateHistoryDAO {

}
