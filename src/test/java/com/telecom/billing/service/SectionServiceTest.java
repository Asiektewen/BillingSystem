/**
 * 
 */
package com.telecom.billing.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.telecom.billing.model.Course;
import com.telecom.billing.model.Faculty;
import com.telecom.billing.model.Section;

/**
 * @author zhangle
 *
 */
public class SectionServiceTest extends ServiceImplTestBase {
	@Test
	@Transactional
	public void testCreateSection() {
		List<Section> list = new ArrayList<Section>();
		Faculty faculty = createOneNewFaculty();
		facultyService.save(faculty);
		for (int i = 0; i < 10; i++) {
			Section s = new Section();
			Course c = createOneNewCourse();
			c = courseService.saveCourse(c);
			s.setCourse(c);
			s.setFaculty(faculty);
			s.setEndDate(DateUtils.addDays(new Date(), 32));
			s.setSeats(30);
			s.setStartDate(DateUtils.addDays(new Date(), 2));
			s = sectionService.saveSection(s);
			list.add(s);

		}
		assertNotEquals(list.size(), 0);
		assertNotNull(list.get(0));
	}

}
