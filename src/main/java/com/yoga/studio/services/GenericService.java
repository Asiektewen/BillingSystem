/**
 * 
 */
package com.yoga.studio.services;

import java.util.List;

/**
 * @author zhangle
 *
 */
public interface GenericService<T> {
	public T findOne(final Long id);

	public List<T> findAll();

	public T save(final T entity);

	public T update(final T entity);

	public void delete(final T entity);

	public void deleteById(final long id);

	public Integer countAll();
}
