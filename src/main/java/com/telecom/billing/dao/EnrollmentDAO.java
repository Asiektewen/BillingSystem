package com.telecom.billing.dao;

import java.util.Date;
import java.util.List;

import com.telecom.billing.model.Enrollment;
import com.telecom.billing.model.Section;

public interface EnrollmentDAO {
	
	/*
	 * CREATE and UPDATE
	 */
	public void saveEnrollment(Enrollment enrollment); // create and update

	/*
	 * READ
	 */
	public List<Enrollment> listEnrollments();
	
	public Enrollment getEnrollment(Long id);

	/*
	 * DELETE
	 */
	public void deleteEnrollment(Long id);

	public List<Enrollment> listEnrollments(long id, Date now);

	public List<Enrollment> getEnrollment(long id, String string);
	


}
