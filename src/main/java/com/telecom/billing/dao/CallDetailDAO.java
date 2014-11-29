/**
 * 
 */
package com.telecom.billing.dao;

import java.util.List;

import com.telecom.billing.model.CallDetail;

/**
 * @author Eric
 * 
 */
public interface CallDetailDAO extends GenericDAO<CallDetail>{

	public void importCallDetail(List<CallDetail> callDetails);

}
