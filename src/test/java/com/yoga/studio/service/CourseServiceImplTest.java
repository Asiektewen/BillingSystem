/**
 * 
 */
package com.yoga.studio.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yoga.studio.model.Course;
import com.yoga.studio.services.CourseService;

/**
 * @author zhangle
 *
 */
public class CourseServiceImplTest extends ServiceImplTestBase {
	@Autowired
	protected CourseService courseService;

	@Test
	@Transactional
	public void testCreateCourse() {
		Course c = createOneNewCourse();
		c = courseService.saveCourse(c);
		assertNotNull(c.getId());
	}
}
