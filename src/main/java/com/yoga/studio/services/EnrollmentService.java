package com.yoga.studio.services;

import java.util.Date;
import java.util.List;

import com.yoga.studio.model.Enrollment;
import com.yoga.studio.model.Section;


public interface EnrollmentService {
	/*
	 * CREATE and UPDATE 
	 */
	public void saveEnrollment(Enrollment enrollment);

	/*
	 * READ
	 */
	public List<Enrollment> listEnrollments();
	
	public Enrollment getEnrollment(Long id);

	/*
	 * DELETE
	 */
	public void deleteEnrollment(Long id);

	public List<Enrollment> getEnrollments(long id, Date now);

	public List<Enrollment> getEnrollment(long id, String string);



}
