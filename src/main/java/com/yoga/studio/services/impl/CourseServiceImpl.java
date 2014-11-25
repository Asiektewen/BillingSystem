package com.yoga.studio.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoga.studio.dao.CourseDAO;
import com.yoga.studio.model.Course;
import com.yoga.studio.services.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseDAO courseDAO;

	@Transactional
	public Course saveCourse(Course course) {
		// TODO Auto-generated method stub
		return courseDAO.saveCourse(course);

	}

	@Transactional
	public List<Course> listCourses() {
		// TODO Auto-generated method stub
		return courseDAO.listCourses();
	}

	@Transactional
	public Course getCourse(Long id) {
		return courseDAO.getCourse(id);
	}

	@Transactional
	public void deleteCourse(Long id) {
		// TODO Auto-generated method stub
		courseDAO.deleteCourse(id);

	}
	@Transactional
	public List<Course> getRemainPrerequisites(Long id)
	{
		return courseDAO.getRemainPrerequisites(id);
	}
}
