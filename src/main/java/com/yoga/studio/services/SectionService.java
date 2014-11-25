package com.yoga.studio.services;

import java.util.List;

import com.yoga.studio.model.Section;

public interface SectionService {
	/*
	 * CREATE and UPDATE
	 */
	public Section saveSection(Section section);

	/*
	 * READ
	 */
	public List<Section> listSections();

	public Section getSection(Long id);

	/*
	 * DELETE
	 */
	public void deleteSection(Long id);

	List<Section> listCurrentSections(long l);

}
