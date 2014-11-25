/**
 * 
 */
package com.yoga.studio.services;

import java.util.List;

import com.yoga.studio.model.Faculty;
import com.yoga.studio.model.Waiver;

/**
 * @author zhangle
 *
 */
public interface WaiverService extends GenericService<Waiver> {
	public List<Waiver> getWaiverByFaculty(Faculty faculty);
}
