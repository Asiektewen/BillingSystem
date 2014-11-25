/**
 * 
 */
package com.telecom.billing.dao.impl;

import org.springframework.stereotype.Repository;

import com.telecom.billing.dao.FacultyDAO;
import com.telecom.billing.model.Faculty;

/**
 * @author zhangle
 *
 */
@Repository("facultyDAO")
public class FacultyDAOImpl extends GenericDAOImpl<Faculty> implements
		FacultyDAO {

}