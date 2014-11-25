package com.telecom.billing.dao;

import java.util.List;

import com.telecom.billing.model.Waiver;

public interface WaiverDAO extends GenericDAO<Waiver> {
	public List<Waiver> getWaiverByFacultyID(Long id);
}
