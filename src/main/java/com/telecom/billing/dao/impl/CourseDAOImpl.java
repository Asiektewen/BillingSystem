package com.telecom.billing.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.telecom.billing.dao.CourseDAO;
import com.telecom.billing.model.Course;
import com.telecom.billing.model.Customer;
import com.telecom.billing.model.Section;

@Repository
@Transactional(propagation=Propagation.MANDATORY)
public class CourseDAOImpl implements CourseDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Course saveCourse(Course course) {
		return (Course) sessionFactory.getCurrentSession().merge(course);
	}

	@Override
	public List<Course> listCourses() {
		// TODO Auto-generated method stub
		 return sessionFactory.getCurrentSession().createCriteria(Course.class).list();
	}

	@Override
	public Course getCourse(Long id) {
		return (Course) sessionFactory.getCurrentSession().get(Course.class,id);
	}

	@Override
	public void deleteCourse(Long id) {
		Course course = getCourse(id);
		if(course!= null)
			sessionFactory.getCurrentSession().delete(course);
		
	}
	
	private boolean contains(List<Course> list, Course course)
	{
		for (Course curCourse : list) 
		{
			if(curCourse.getId() == course.getId())
			{
				return true;
			}
		}		
		return false;
	}
	@Override
	public List<Course> getRemainPrerequisites(Long id)
	{
		Course course = getCourse(id);
		//List<Course> list = listCourses();
		List<Course> listRemain = new ArrayList<Course>(); 
		for (Course curCourse : listCourses()) 
		{
			if(curCourse.getId() != course.getId() &&  !contains(course.getPrerequisites(), curCourse))
				listRemain.add(curCourse);
		}
		
		return listRemain;
	}
}
