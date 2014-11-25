package com.yoga.studio.dao;

import java.util.List;

import com.yoga.studio.model.Waiver;

public interface WaiverDAO extends GenericDAO<Waiver> {
	public List<Waiver> getWaiverByFacultyID(Long id);
}
