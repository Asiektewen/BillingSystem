package com.yoga.studio.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Course {

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<Section> sections;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Course> prerequisites;

	@Id
	@GeneratedValue
	private Long id;

	private String title;

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public List<Course> getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(List<Course> prerequisites) {
		this.prerequisites = prerequisites;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		Course course;
		if (obj instanceof Course) {
			course = (Course) obj;
			result = (this.id == course.id && this.title.equals(course.title));
		}
		return result;
	}

	public void removePrerequisite(Course prerequisite) {
		Course find = null;
		for (Course item : prerequisites) {
			if (item.getId() == prerequisite.getId())
				find = item;
		}
		if (find != null)
			prerequisites.remove(find);
	}

}
