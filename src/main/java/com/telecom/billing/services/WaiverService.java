/**
 * 
 */
package com.telecom.billing.services;

import java.util.List;

import com.telecom.billing.model.Faculty;
import com.telecom.billing.model.Waiver;

/**
 * @author zhangle
 *
 */
public interface WaiverService extends GenericService<Waiver> {
	public List<Waiver> getWaiverByFaculty(Faculty faculty);
}
