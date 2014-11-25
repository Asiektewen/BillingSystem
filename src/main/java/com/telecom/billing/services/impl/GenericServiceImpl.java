/**
 * 
 */
package com.telecom.billing.services.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.telecom.billing.dao.GenericDAO;
import com.telecom.billing.services.GenericService;

/**
 * @author zhangle
 *
 */
public abstract class GenericServiceImpl<T> implements GenericService<T> {
	protected abstract GenericDAO getDAO();

	@Override
	public T findOne(Long id) {
		return (T) getDAO().findOne(id);
	}

	@Override
	public List<T> findAll() {
		return getDAO().findAll();
	}

	@Override
	@Transactional
	public T save(T entity) {
		return (T) getDAO().save(entity);
	}

	@Override
	@Transactional
	public T update(T entity) {
		return (T) getDAO().update(entity);
	}

	@Override
	@Transactional
	public void delete(T entity) {
		getDAO().delete(entity);
	}

	@Override
	@Transactional
	public void deleteById(long id) {
		getDAO().deleteById(id);
	}

	@Override
	public Integer countAll() {
		return getDAO().countAll();
	}
}
