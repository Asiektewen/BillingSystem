package com.yoga.studio.dao;

import java.util.List;

import com.yoga.studio.model.Course;

public interface CourseDAO {
	
	/*
	 * CREATE and UPDATE
	 */
	public Course saveCourse(Course course); // create and update

	/*
	 * READ
	 */
	public List<Course> listCourses();
	
	public Course getCourse(Long id);

	/*
	 * DELETE
	 */
	public void deleteCourse(Long id);
	
	public List<Course> getRemainPrerequisites(Long id);
}
