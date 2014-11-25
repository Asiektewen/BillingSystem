/**
 * 
 */
package com.telecom.billing.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.telecom.billing.dao.FacultyDAO;
import com.telecom.billing.dao.GenericDAO;
import com.telecom.billing.model.Faculty;
import com.telecom.billing.services.FacultyService;

/**
 * @author zhangle
 *
 */
@Service("facultyService")
public class FacultyServiceImpl extends GenericServiceImpl<Faculty> implements
		FacultyService {
	@Autowired
	@Qualifier("facultyDAO")
	public FacultyDAO facultyDAO;

	@Override
	protected GenericDAO getDAO() {
		return facultyDAO;
	}

}
