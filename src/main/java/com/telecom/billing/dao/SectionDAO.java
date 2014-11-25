package com.telecom.billing.dao;

import java.util.List;

import com.telecom.billing.model.Section;

public interface SectionDAO {

	/*
	 * CREATE and UPDATE
	 */
	public Section saveSection(Section section); // create and update

	/*
	 * READ
	 */
	public List<Section> listSections();

	public Section getSection(Long id);

	/*
	 * DELETE
	 */
	public void deleteSection(Long id);

	public boolean checkLogin(String userName, String userPassword);

	List<Section> listCurrentSections(long id);

}
