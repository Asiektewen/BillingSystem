package com.yoga.studio.services;

import java.util.List;

import com.yoga.studio.model.Course;

public interface CourseService {
	/*
	 * CREATE and UPDATE
	 */
	public Course saveCourse(Course course);

	/*
	 * READ
	 */
	public List<Course> listCourses();

	public Course getCourse(Long id);

	/*
	 * DELETE
	 */
	public void deleteCourse(Long id);
	
	public List<Course> getRemainPrerequisites(Long courseId);
}
