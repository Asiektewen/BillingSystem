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
public interface CallDetailDAO {

	public void importCallDetail(List<CallDetail> callDetails);

}
