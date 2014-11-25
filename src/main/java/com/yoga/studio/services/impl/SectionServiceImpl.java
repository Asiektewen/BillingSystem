package com.yoga.studio.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoga.studio.dao.SectionDAO;
import com.yoga.studio.model.Section;
import com.yoga.studio.services.SectionService;

@Service
public class SectionServiceImpl implements SectionService {

	@Autowired
	SectionDAO sectionDAO;

	@Transactional
	public Section saveSection(Section section) {
		// TODO Auto-generated method stub
		return sectionDAO.saveSection(section);

	}

	@Transactional
	public List<Section> listSections() {
		// TODO Auto-generated method stub
		return sectionDAO.listSections();
	}

	@Transactional
	public Section getSection(Long id) {
		// TODO Auto-generated method stub
		return sectionDAO.getSection(id);
	}

	@Transactional
	public void deleteSection(Long id) {
		// TODO Auto-generated method stub
		sectionDAO.deleteSection(id);

	}

	@Override
	public List<Section> listCurrentSections(long id) {
		// TODO Auto-generated method stub
		return sectionDAO.listCurrentSections(id);
	}

}
