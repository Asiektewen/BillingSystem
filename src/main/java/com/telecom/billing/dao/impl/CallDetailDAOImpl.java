/**
 * 
 */
package com.telecom.billing.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.telecom.billing.dao.CallDetailDAO;
import com.telecom.billing.model.CallDetail;

/**
 * @author Eric
 * 
 */
@Repository("callDetailDAO")
public class CallDetailDAOImpl extends GenericDAOImpl<CallDetail> implements
		CallDetailDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.telecom.billing.dao.CallDetailDAO#importCallDetail(java.util.List)
	 */
	@Override
	public void importCallDetail(List<CallDetail> callDetails) {
		for (CallDetail call : callDetails) {
			this.save(call);
		}

	}

}
