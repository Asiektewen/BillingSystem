/**
 * 
 */
package com.yoga.studio.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yoga.studio.dao.WaiverDAO;
import com.yoga.studio.model.Waiver;

/**
 * @author zhangle
 *
 */
@Repository("waiverDAO")
public class WaiverDAOImpl extends GenericDAOImpl<Waiver> implements WaiverDAO {

	@Override
	public List<Waiver> getWaiverByFacultyID(Long id) {
		String sql = "FROM Waiver AS W WHERE W.customer.faculty.id= ? ";
		Query query = getCurrentSession().createQuery(sql);
		query.setParameter(0, id);
		List<Waiver> results = query.list();
		return results;
	}

}
