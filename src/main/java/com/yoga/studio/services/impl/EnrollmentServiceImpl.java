package com.yoga.studio.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoga.studio.dao.EnrollmentDAO;
import com.yoga.studio.model.Enrollment;
import com.yoga.studio.model.Section;
import com.yoga.studio.services.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService{
	
	@Autowired
	EnrollmentDAO enrollmentDAO;

	@Transactional
	public void saveEnrollment(Enrollment enrollment) {
		// TODO Auto-generated method stub
		enrollmentDAO.saveEnrollment(enrollment);
		
	}

	@Transactional
	public List<Enrollment> listEnrollments() {
		// TODO Auto-generated method stub
		return enrollmentDAO.listEnrollments();
	}

	@Transactional
	public Enrollment getEnrollment(Long id) {
		// TODO Auto-generated method stub
		return enrollmentDAO.getEnrollment(id);
	}

	@Transactional
	public void deleteEnrollment(Long id) {
		// TODO Auto-generated method stub
		enrollmentDAO.deleteEnrollment(id);
	}

	@Override
	public List<Enrollment> getEnrollments(long id, Date now) {
		// TODO Auto-generated method stub
		return enrollmentDAO.listEnrollments(id,now);
	}

	@Override
	public List<Enrollment> getEnrollment(long id, String string) {
		// TODO Auto-generated method stub
		return enrollmentDAO.getEnrollment(id,string);
	}



	

}
