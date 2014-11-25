/**
 * 
 */
package com.yoga.studio.dao.impl;

import org.springframework.stereotype.Repository;

import com.yoga.studio.dao.FacultyDAO;
import com.yoga.studio.model.Faculty;

/**
 * @author zhangle
 *
 */
@Repository("facultyDAO")
public class FacultyDAOImpl extends GenericDAOImpl<Faculty> implements
		FacultyDAO {

}