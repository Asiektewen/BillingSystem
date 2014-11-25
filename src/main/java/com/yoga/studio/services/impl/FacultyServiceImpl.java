/**
 * 
 */
package com.yoga.studio.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yoga.studio.dao.FacultyDAO;
import com.yoga.studio.dao.GenericDAO;
import com.yoga.studio.model.Faculty;
import com.yoga.studio.services.FacultyService;

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
