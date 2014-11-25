/**
 * 
 */
package com.yoga.studio.dao;

import java.util.List;

/**
 * @author zhangle
 * @param <T>
 *
 */
public interface GenericDAO<T> {
	public T findOne(final long id);

	public List<T> findAll();

	public T save(final T entity);

	public T update(final T entity);

	public void delete(final T entity);

	public void deleteById(final long id);

	public Integer countAll();
}
