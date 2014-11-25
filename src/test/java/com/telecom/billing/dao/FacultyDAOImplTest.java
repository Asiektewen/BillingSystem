/**
 * 
 */
package com.telecom.billing.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.telecom.billing.dao.FacultyDAO;
import com.telecom.billing.model.Faculty;

/**
 * @author zhangle
 *
 */
public class FacultyDAOImplTest extends DAOImplTestBase {
	@Autowired
	@Qualifier("facultyDAO")
	private FacultyDAO facultyDAO;

	@Test
	@Transactional
	public void testFacultyDAO() {
		Faculty faculty = facultyDAO.save(createOneNewFaculty());
		Faculty newFaculty = facultyDAO.findOne(faculty.getId());
		assertNotNull(faculty.getId());
		assertNotNull(newFaculty);
	}
}
