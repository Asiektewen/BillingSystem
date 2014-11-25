/**
 * 
 */
package com.yoga.studio.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoga.studio.dao.GenericDAO;
import com.yoga.studio.dao.WaiverDAO;
import com.yoga.studio.model.Faculty;
import com.yoga.studio.model.Waiver;
import com.yoga.studio.services.WaiverService;

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
