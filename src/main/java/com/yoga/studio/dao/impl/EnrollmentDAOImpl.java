package com.yoga.studio.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yoga.studio.dao.EnrollmentDAO;
import com.yoga.studio.model.Enrollment;
import com.yoga.studio.model.Customer;
import com.yoga.studio.model.Section;

@Repository
public class EnrollmentDAOImpl implements EnrollmentDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void saveEnrollment(Enrollment enrollment) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().merge(enrollment);
	}

	@Override
	public List<Enrollment> listEnrollments() {
		// TODO Auto-generated method stub
		 return sessionFactory.getCurrentSession().createCriteria(Enrollment.class).list();
	}

	@Override
	public Enrollment getEnrollment(Long id) {
		// TODO Auto-generated method stub
		return (Enrollment) sessionFactory.getCurrentSession().get(
				Enrollment.class, id);	
		}

	@Override
	public void deleteEnrollment(Long id) {
		// TODO Auto-generated method stub
		Enrollment enrollment = getEnrollment(id);
		 Query query = sessionFactory.getCurrentSession().createQuery(" delete Enrollment as e where e.id=? ");
		    query.setParameter(0,enrollment.getId());
		    //query.setParameter(1, now);
		    query.executeUpdate();
	}

	@Override
	public List<Enrollment> listEnrollments(long id, Date now) {
		// TODO Auto-generated method stub
		Customer customer = (Customer) sessionFactory.getCurrentSession().get(
				Customer.class, id);
	    Query query = sessionFactory.getCurrentSession().createQuery(" from Enrollment as e where e.customer=? and e.section.startDate>? ");
	    query.setParameter(0,customer);
	    query.setParameter(1, now);
	    List list = query.list();
	    //session.close();

	    

	    
		return list;	
		}

	@Override
	public List<Enrollment> getEnrollment(long id, String string) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery(" from Enrollment as e where e.section.id=? and e.enrollmentStatus=? ");
	    query.setParameter(0,id);
	    query.setParameter(1, string);
	    List list = query.list();
	    
	    return list;
	}

	


}
