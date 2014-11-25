/**
 * 
 */
package com.telecom.billing.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telecom.billing.dao.GenericDAO;
import com.telecom.billing.dao.WaiverDAO;
import com.telecom.billing.model.Faculty;
import com.telecom.billing.model.Waiver;
import com.telecom.billing.services.WaiverService;

/**
 * @author zhangle
 *
 */
@Service("waiverService")
public class WaiverServiceImpl extends GenericServiceImpl<Waiver> implements
		WaiverService {

	@Autowired
	@Qualifier("waiverDAO")
	public WaiverDAO waiverDAO;

	@Override
	protected GenericDAO getDAO() {
		return waiverDAO;
	}

	@Override
	@Transactional
	public List<Waiver> getWaiverByFaculty(Faculty faculty) {
		return waiverDAO.getWaiverByFacultyID(faculty.getId());
	}
}
